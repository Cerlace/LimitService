package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.external.ExchangeRateApiClient;
import cerlace.limitservice.core.mapper.ExchangeRateMapper;
import cerlace.limitservice.core.mapper.TransactionMapper;
import cerlace.limitservice.core.service.TransactionService;
import cerlace.limitservice.core.utils.Constants;
import cerlace.limitservice.core.utils.DateTimeUtils;
import cerlace.limitservice.core.utils.MappingUtils;
import cerlace.limitservice.core.utils.SpendLimitUtils;
import cerlace.limitservice.persistence.entity.ExchangeRate;
import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.entity.Transaction;
import cerlace.limitservice.persistence.repository.ExchangeRateRepository;
import cerlace.limitservice.persistence.repository.SpendLimitRepository;
import cerlace.limitservice.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;
    private final ExchangeRateMapper exchangeRateMapper;

    private final TransactionRepository transactionRepository;
    private final SpendLimitRepository spendLimitRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateApiClient exchangeRateApiClient;

    @Transactional
    @Override
    public TransactionResponse saveTransaction(TransactionCreateRequest request) {
        Transaction transaction = transactionMapper.toEntity(request);

        Optional<SpendLimit> spendLimitOptional = spendLimitRepository
                .findTopByExpenseCategoryOrderByDatetimeDesc(transaction.getExpenseCategory());
        SpendLimit spendLimit;
        if (spendLimitOptional.isEmpty() ||
                !DateTimeUtils.isCurrentMonth(spendLimitOptional.get().getDatetime())) {
            spendLimit = spendLimitRepository.save(
                    SpendLimitUtils.getBaseSpendLimit(transaction.getExpenseCategory()));
        } else {
            spendLimit = spendLimitOptional.get();
        }

        transaction.setSpendLimit(spendLimit);

        if (transaction.getCurrencyShortname().equals(Constants.USD_SHORTNAME)) {
            transaction.setUsdSum(transaction.getSum());
        } else {
            Optional<ExchangeRate> exchangeRateOptional = exchangeRateRepository
                    .findByCurrencyShortnameAndDate(
                            transaction.getCurrencyShortname(),
                            LocalDate.from(transaction.getDatetime()));
            ExchangeRate exchangeRate = exchangeRateOptional.orElseGet(() -> {
                ExchangeRate requestedRate = exchangeRateMapper.toEntity(
                        exchangeRateApiClient.getExchangeRate(
                                MappingUtils.currencyShortnameToPair(transaction.getCurrencyShortname()),
                                LocalDate.from(transaction.getDatetime())
                        ));
                requestedRate.setDate(LocalDate.from(transaction.getDatetime()));
                return exchangeRateRepository.save(requestedRate);
            });

            transaction.setUsdSum(transaction.getSum().multiply(exchangeRate.getRate()));
        }

        BigDecimal monthTransactionsSum = transactionRepository
                .sumByCategoryAndDatetime(
                        transaction.getExpenseCategory(),
                        DateTimeUtils.getMonthStart(transaction.getDatetime()),
                        DateTimeUtils.getMonthEnd(transaction.getDatetime()))
                .orElse(BigDecimal.ZERO);

        transaction.setLimitExceeded(
                monthTransactionsSum
                        .add(transaction.getUsdSum())
                        .compareTo(spendLimit.getUsdSum()) > 0
        );

        return transactionMapper.toDto(transactionRepository.save(transaction));
    }
}
