package cerlace.limitservice.core.external;

import cerlace.limitservice.core.dto.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Feign-клиент для взаимодействия с внешним сервисом получения курсов валют.
 * <p>
 * Использует указанный URL сервиса, определённый в настройках приложения
 * (свойство {@code exchange-rate-api.url}).
 * </p>
 */
@FeignClient(
        name = "exchangeRateApiClient",
        url = "${exchange-rate-api.url}"
)
public interface ExchangeRateApiClient {

    /**
     * Получает курс обмена для заданной валютной пары на определённую дату.
     *
     * @param currencyPair валютная пара в формате "XXX/YYY", где XXX — валюта отправления,
     *                     YYY — валюта получения (например, "RUB/USD").
     * @param date         дата, на которую требуется получить курс обмена (в формате ISO, например, "2024-06-01").
     * @return объект {@link ExchangeRateResponse}, содержащий информацию о курсе обмена.
     */
    @GetMapping("/exchange_rate")
    ExchangeRateResponse getExchangeRate(
            @RequestParam("symbol") String currencyPair,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
