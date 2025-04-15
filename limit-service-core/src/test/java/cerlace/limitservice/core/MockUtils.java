package cerlace.limitservice.core;

import cerlace.limitservice.core.dto.ExchangeRateResponse;
import cerlace.limitservice.core.dto.SpendLimitCreateRequest;
import cerlace.limitservice.persistence.entity.ExchangeRate;
import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.entity.Transaction;
import cerlace.limitservice.persistence.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static cerlace.limitservice.core.MockConstants.*;

public class MockUtils {

    public static SpendLimitCreateRequest createSpendLimitCreateRequest() {
        return SpendLimitCreateRequest.builder()
                .usdSum(TEST_TRANSACTIONS_SUM)
                .expenseCategory(ExpenseCategory.SERVICE)
                .build();
    }

    public static SpendLimit createLimit(BigDecimal usdSum) {
        return SpendLimit.builder()
                .usdSum(usdSum)
                .expenseCategory(ExpenseCategory.SERVICE)
                .build();
    }

    public static Transaction createTransaction(boolean limitExceeded, SpendLimit limit) {
        return Transaction.builder()
                .accountFrom(ACCOUNT_FROM)
                .accountTo(ACCOUNT_TO)
                .currencyShortname(USD_SHORTNAME)
                .sum(TEST_SUM)
                .usdSum(TEST_SUM)
                .expenseCategory(ExpenseCategory.SERVICE)
                .datetime(OffsetDateTime.now())
                .limitExceeded(limitExceeded)
                .spendLimit(limit)
                .build();
    }

    public static ExchangeRateResponse createApiResponse() {
        return ExchangeRateResponse.builder()
                .currencyPair(TEST_PAIR)
                .rate(TEST_RATE)
                .build();
    }

    public static ExchangeRate createTestRate() {
        return ExchangeRate.builder()
                .currencyShortname(EUR_SHORTNAME)
                .date(TEST_LOCAL_DATE)
                .rate(TEST_RATE)
                .build();
    }
}
