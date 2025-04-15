package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.mapper.TransactionMapper;
import cerlace.limitservice.core.service.ExchangeRateService;
import cerlace.limitservice.core.service.SpendLimitService;
import cerlace.limitservice.core.service.TransactionService;
import cerlace.limitservice.core.utils.DateTimeUtils;
import cerlace.limitservice.core.utils.SpendLimitUtils;
import cerlace.limitservice.persistence.entity.Transaction;
import cerlace.limitservice.persistence.enums.ExpenseCategory;
import cerlace.limitservice.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

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

        BigDecimal monthTransactionsSum = getMonthTransactionsSum(
                transaction.getExpenseCategory(),
                transaction.getDatetime());

        transaction.setLimitExceeded(
                SpendLimitUtils.isLimitExceeded(
                        monthTransactionsSum.add(transaction.getUsdSum()),
                        transaction.getSpendLimit()
                )
        );

        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    @Transactional(readOnly = true)
    @Override
    public BigDecimal getMonthTransactionsSum(ExpenseCategory category, OffsetDateTime dateTime) {
        return transactionRepository
                .sumByCategoryAndDatetime(
                        category,
                        DateTimeUtils.getMonthStart(dateTime),
                        DateTimeUtils.getMonthEnd(dateTime))
                .orElse(BigDecimal.ZERO);
    }
}
