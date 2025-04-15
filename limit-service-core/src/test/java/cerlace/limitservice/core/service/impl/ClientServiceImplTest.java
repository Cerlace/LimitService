package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.MockUtils;
import cerlace.limitservice.core.dto.SpendLimitCreateRequest;
import cerlace.limitservice.core.dto.SpendLimitResponse;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.service.ClientService;
import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.entity.Transaction;
import cerlace.limitservice.persistence.repository.SpendLimitRepository;
import cerlace.limitservice.persistence.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static cerlace.limitservice.core.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ClientServiceImplTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private SpendLimitRepository spendLimitRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void setSpendLimitTest() {
        SpendLimitCreateRequest request = MockUtils.createSpendLimitCreateRequest();

        SpendLimitResponse response = clientService.setSpendLimit(request);
        SpendLimit persistedEntity = spendLimitRepository.findAll().get(0);

        assertEquals(1, spendLimitRepository.count());
        assertEquals(request.getUsdSum(), persistedEntity.getUsdSum());
        assertEquals(response.getUsdSum(), persistedEntity.getUsdSum());
    }

    @Test
    void getAllSpendLimitTest() {
        SpendLimit oldLimit = MockUtils.createLimit(OLD_LIMIT_SUM);
        spendLimitRepository.save(oldLimit);
        SpendLimit newLimit = MockUtils.createLimit(NEW_LIMIT_SUM);
        spendLimitRepository.save(newLimit);

        List<SpendLimitResponse> result = clientService.getAllSpendLimits();

        assertEquals(2, result.size());
    }

    @Test
    void getLimitExceededTransactionTest() {
        SpendLimit spendLimit = MockUtils.createLimit(TEST_LIMIT);
        spendLimitRepository.save(spendLimit);

        Transaction tx1 = MockUtils.createTransaction(true, spendLimit);
        Transaction tx2 = MockUtils.createTransaction(false, spendLimit);
        Transaction tx3 = MockUtils.createTransaction(true, spendLimit);
        transactionRepository.saveAll(List.of(tx1, tx2, tx3));

        List<TransactionResponse> result = clientService.getLimitExceededTransactions();

        assertEquals(2, result.size());
    }
}