package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.MockUtils;
import cerlace.limitservice.core.service.SpendLimitService;
import cerlace.limitservice.core.utils.Constants;
import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;
import cerlace.limitservice.persistence.repository.SpendLimitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static cerlace.limitservice.core.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class SpendLimitServiceImplTest {

    @Autowired
    private SpendLimitService spendLimitService;

    @Autowired
    private SpendLimitRepository spendLimitRepository;

    @Test
    void getCurrentSpendLimitTest() {
        SpendLimit savedLimit = MockUtils.createLimit(TEST_LIMIT);
        spendLimitRepository.save(savedLimit);

        SpendLimit currentLimit = spendLimitService.getCurrentSpendLimit(savedLimit.getExpenseCategory());
        assertEquals(savedLimit.getUsdSum(), currentLimit.getUsdSum());

        spendLimitRepository.deleteAll();
        SpendLimit baseLimit = spendLimitService.getCurrentSpendLimit(ExpenseCategory.SERVICE);
        assertEquals(Constants.BASE_LIMIT, baseLimit.getUsdSum());
    }
}