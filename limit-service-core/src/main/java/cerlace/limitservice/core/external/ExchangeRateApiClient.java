package cerlace.limitservice.core.external;

import cerlace.limitservice.core.dto.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FeignClient(
        name = "exchangeRateApiClient",
        url = "${exchange-rate-api.url}"
)
public interface ExchangeRateApiClient {
    @GetMapping("/exchange_rate")
    ExchangeRateResponse getExchangeRate(
            @RequestParam("symbol") String currencyPair,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
