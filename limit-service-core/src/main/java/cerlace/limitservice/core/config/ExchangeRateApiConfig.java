package cerlace.limitservice.core.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки внешнего API курсов валют.
 * <p>
 * Используется для создания перехватчика запросов, добавляющего ключ API к запросам.
 * </p>
 */
@Configuration
public class ExchangeRateApiConfig {

    /**
     * Ключ API для доступа к сервису курсов валют.
     * <p>
     * Значение загружается из настроек приложения (свойство {@code exchange-rate-api.key}).
     * </p>
     */
    @Value("${exchange-rate-api.key}")
    private String apiKey;

    /**
     * Создаёт перехватчик запросов, добавляющий ключ API к каждому запросу.
     *
     * @return перехватчик запросов с ключом API
     */
    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return requestTemplate -> requestTemplate.query("apikey", apiKey);
    }
}
