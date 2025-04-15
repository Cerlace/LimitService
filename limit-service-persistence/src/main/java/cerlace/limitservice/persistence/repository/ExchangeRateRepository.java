package cerlace.limitservice.persistence.repository;

import cerlace.limitservice.persistence.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с курсами валют.
 * <p>
 * Предоставляет методы доступа к данным курсов валют в базе данных,
 * включая поиск по критериям. Наследует базовые CRUD-операции
 * из {@link JpaRepository}.
 *
 * @see JpaRepository
 * @see ExchangeRate
 */
@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, UUID> {

    /**
     * Находит курс валюты по её краткому названию и дате.
     * <p>
     * Пример использования:
     * <pre>{@code
     * Optional<ExchangeRate> rate = repository.findByCurrencyShortnameAndDate("USD", LocalDate.now());
     * }</pre>
     *
     * @param currencyShortname краткое название валюты (например: USD, EUR)
     * @param date               дата актуальности курса
     * @return {@link Optional} с найденным курсом валюты,
     *         или {@link Optional#empty()}, если курс не найден
     */
    Optional<ExchangeRate> findByCurrencyShortnameAndDate(String currencyShortname, LocalDate date);
}
