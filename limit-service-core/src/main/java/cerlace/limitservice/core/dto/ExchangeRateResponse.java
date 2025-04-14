package cerlace.limitservice.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

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
