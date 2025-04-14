package cerlace.limitservice.core.utils;

import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;

public class DateTimeUtils {
    public static boolean isCurrentMonth(TemporalAccessor temporal) {
        YearMonth current = YearMonth.now(ZoneId.from(temporal));
        return current.equals(YearMonth.from(temporal));
    }

    public static OffsetDateTime getMonthStart(OffsetDateTime dateTime) {
        return dateTime
                .with(TemporalAdjusters.firstDayOfMonth())
                .toLocalDate()
                .atStartOfDay()
                .atOffset(dateTime.getOffset());
    }

    public static OffsetDateTime getMonthEnd(OffsetDateTime dateTime) {
        return dateTime
                .with(TemporalAdjusters.lastDayOfMonth())
                .toLocalDate()
                .atTime(23, 59, 59)
                .atOffset(dateTime.getOffset());
    }
}
