package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.MockUtils;
import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.service.TransactionService;
import cerlace.limitservice.core.utils.Constants;
import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.entity.Transaction;
import cerlace.limitservice.persistence.enums.ExpenseCategory;
import cerlace.limitservice.persistence.repository.SpendLimitRepository;
import cerlace.limitservice.persistence.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static cerlace.limitservice.core.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class TransactionServiceImplTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SpendLimitRepository spendLimitRepository;

    @Test
    void saveTransaction() {
        TransactionCreateRequest request = MockUtils.createTransactionCreateRequest();

        TransactionResponse response = transactionService.saveTransaction(request);

        assertEquals(request.getAccountFrom(), response.getAccountFrom());
        assertEquals(request.getSum(), response.getSum());
        assertEquals(0, Constants.BASE_LIMIT.compareTo(response.getLimitSum()));
    }

    @Test
    void getMonthTransactionsSum() {
        SpendLimit spendLimit = MockUtils.createLimit(TEST_LIMIT);
        spendLimitRepository.save(spendLimit);

        Transaction transaction = MockUtils.createTransaction(true, spendLimit);
        transactionRepository.save(transaction);

        BigDecimal monthSum = transactionService.getMonthTransactionsSum(
                transaction.getExpenseCategory(),
                transaction.getDatetime()
        );

        assertEquals(0, monthSum.compareTo(transaction.getUsdSum()));
    }
}