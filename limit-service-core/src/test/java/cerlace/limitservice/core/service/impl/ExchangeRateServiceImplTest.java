package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.MockUtils;
import cerlace.limitservice.core.dto.ExchangeRateResponse;
import cerlace.limitservice.core.external.ExchangeRateApiClient;
import cerlace.limitservice.core.service.ExchangeRateService;
import cerlace.limitservice.persistence.entity.ExchangeRate;
import cerlace.limitservice.persistence.repository.ExchangeRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static cerlace.limitservice.core.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ExchangeRateServiceImplTest {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @MockitoBean
    private ExchangeRateApiClient exchangeRateApiClient;

    @BeforeEach
    void setUp() {
        ExchangeRateResponse apiResponse = MockUtils.createApiResponse();
        Mockito.when(exchangeRateApiClient.getExchangeRate(anyString(), any()))
                .thenReturn(apiResponse);
    }

    @Test
    void convertCurrencyToUsdTest() {
        BigDecimal usdConvertResult = exchangeRateService.convertCurrencyToUsd(
                USD_SHORTNAME,
                TEST_SUM,
                TEST_LOCAL_DATE);

        assertEquals(0, TEST_SUM.compareTo(usdConvertResult));

        ExchangeRate rate = ExchangeRate.builder()
                .currencyShortname(EUR_SHORTNAME)
                .date(TEST_LOCAL_DATE)
                .rate(TEST_RATE)
                .build();

        exchangeRateRepository.save(rate);

        BigDecimal eurConvertResult = exchangeRateService.convertCurrencyToUsd(
                EUR_SHORTNAME,
                TEST_SUM,
                TEST_LOCAL_DATE);

        assertEquals(0, EXPECTED_SUM.compareTo(eurConvertResult));
    }

    @Test
    void getExchangeRateTest() {

        ExchangeRate savedRate = MockUtils.createTestRate();
        exchangeRateRepository.save(savedRate);

        ExchangeRate result = exchangeRateService.getExchangeRate(EUR_SHORTNAME, TEST_LOCAL_DATE);

        assertEquals(0, savedRate.getRate().compareTo(result.getRate()));
    }

    @Test
    void fetchAndSaveExchangeRateTest() {

        ExchangeRate result = exchangeRateService.fetchAndSaveExchangeRate(TEST_SHORTNAME, TEST_LOCAL_DATE);

        assertEquals(TEST_SHORTNAME, result.getCurrencyShortname());
        assertEquals(TEST_LOCAL_DATE, result.getDate());
        assertEquals(0, TEST_RATE.compareTo(result.getRate()));
    }
}