package cerlace.limitservice.core;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class MockConstants {

    public static final String USD_SHORTNAME = "USD";
    public static final String EUR_SHORTNAME = "EUR";

    public static final BigDecimal TEST_SUM = BigDecimal.valueOf(100);
    public static final BigDecimal TEST_RATE = BigDecimal.valueOf(1.5);
    public static final BigDecimal EXPECTED_SUM = BigDecimal.valueOf(150);

    public static final OffsetDateTime TEST_DATE = OffsetDateTime.of(
            2025, 2, 14, 12, 0, 0, 0, ZoneOffset.UTC);
    public static final OffsetDateTime EXPECTED_MONTH_START = OffsetDateTime.of(
            2025, 2, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    public static final OffsetDateTime EXPECTED_MONTH_END = OffsetDateTime.of(
            2025, 2, 28, 23, 59, 59, 0, ZoneOffset.UTC);

    public static final String TEST_SHORTNAME = "BYN";
    public static final String TEST_PAIR = "BYN/USD";

    public static final BigDecimal TEST_TRANSACTIONS_SUM = BigDecimal.valueOf(1000);
    public static final BigDecimal TEST_LIMIT = BigDecimal.valueOf(900);
}
