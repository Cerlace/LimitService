package cerlace.limitservice.core.utils;

import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;

/**
 * Утилитарный класс для работы с датой и временем.
 */
public class DateTimeUtils {

    public static final int DAY_END_HOUR = 23;
    public static final int DAY_END_MINUTE = 59;
    public static final int DAY_END_SECOND = 59;

    /**
     * Проверяет, относится ли переданная дата к текущему месяцу.
     *
     * @param temporal объект даты/времени
     * @return {@code true}, если дата в текущем месяце, иначе {@code false}
     */
    public static boolean isCurrentMonth(TemporalAccessor temporal) {
        YearMonth current = YearMonth.now(ZoneId.from(temporal));
        return current.equals(YearMonth.from(temporal));
    }

    /**
     * Возвращает дату и время начала месяца для переданного значения.
     *
     * @param dateTime исходная дата и время
     * @return дата и время начала месяца (00:00:00 первого числа)
     */
    public static OffsetDateTime getMonthStart(OffsetDateTime dateTime) {
        return dateTime
                .with(TemporalAdjusters.firstDayOfMonth())
                .toLocalDate()
                .atStartOfDay()
                .atOffset(dateTime.getOffset());
    }

    /**
     * Возвращает дату и время конца месяца для переданного значения.
     *
     * @param dateTime исходная дата и время
     * @return дата и время конца месяца (23:59:59 последнего числа)
     */
    public static OffsetDateTime getMonthEnd(OffsetDateTime dateTime) {
        return dateTime
                .with(TemporalAdjusters.lastDayOfMonth())
                .toLocalDate()
                .atTime(DAY_END_HOUR, DAY_END_MINUTE, DAY_END_SECOND)
                .atOffset(dateTime.getOffset());
    }
}
