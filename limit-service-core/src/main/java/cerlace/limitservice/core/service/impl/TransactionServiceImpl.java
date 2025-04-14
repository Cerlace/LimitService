package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.mapper.TransactionMapper;
import cerlace.limitservice.core.service.ExchangeRateService;
import cerlace.limitservice.core.service.SpendLimitService;
import cerlace.limitservice.core.service.TransactionService;
import cerlace.limitservice.core.utils.DateTimeUtils;
import cerlace.limitservice.persistence.entity.Transaction;
import cerlace.limitservice.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final SpendLimitService spendLimitService;
    private final ExchangeRateService exchangeRateService;

    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;


    @Transactional
    @Override
    public TransactionResponse saveTransaction(TransactionCreateRequest request) {
        Transaction transaction = transactionMapper.toEntity(request);

        transaction.setSpendLimit(spendLimitService.getCurrentSpendLimit(transaction.getExpenseCategory()));

        transaction.setUsdSum(exchangeRateService.convertCurrencyToUsd(
                transaction.getCurrencyShortname(),
                transaction.getSum(),
                LocalDate.from(transaction.getDatetime())
        ));


        BigDecimal monthTransactionsSum = transactionRepository
                .sumByCategoryAndDatetime(
                        transaction.getExpenseCategory(),
                        DateTimeUtils.getMonthStart(transaction.getDatetime()),
                        DateTimeUtils.getMonthEnd(transaction.getDatetime()))
                .orElse(BigDecimal.ZERO);

        transaction.setLimitExceeded(
                monthTransactionsSum
                        .add(transaction.getUsdSum())
                        .compareTo(transaction.getSpendLimit().getUsdSum()) > 0
        );

        return transactionMapper.toDto(transactionRepository.save(transaction));
    }
}
