package cerlace.limitservice.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO представляющий ответ с информацией об обменном курсе между валютами.
 * <p>
 * Хранит данные о валютной паре, текущем обменном курсе и временной метке,
 * когда курс был получен. Используется для получения актуальной информации
 * об обменных курсах для различных валют.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExchangeRateResponse {
    @JsonProperty("symbol")
    private String currencyPair;
    @JsonProperty("rate")
    private BigDecimal rate;
    @JsonProperty("timestamp")
    private Instant timestamp;
}
