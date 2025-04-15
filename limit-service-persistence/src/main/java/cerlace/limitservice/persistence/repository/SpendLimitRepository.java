package cerlace.limitservice.persistence.repository;

import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с лимитами расходов.
 * <p>
 * Предоставляет методы доступа к данным о лимитах расходов, включая кастомные запросы
 * для получения специфичных данных. Наследует базовые CRUD-операции
 * из {@link JpaRepository}.
 *
 * @see JpaRepository
 * @see SpendLimit
 */
@Repository
public interface SpendLimitRepository extends JpaRepository<SpendLimit, UUID> {
    /**
     * Находит все лимиты расходов, отсортированные по дате создания (от новых к старым).
     * <p>
     * Пример использования:
     * <pre>{@code
     * List<SpendLimit> limits = repository.findAllOrderedByDatetimeDesc();
     * }</pre>
     *
     * @return список лимитов в порядке убывания даты ({@link SpendLimit})
     */
    @Transactional(readOnly = true)
    @Query("SELECT s FROM SpendLimit s ORDER BY s.datetime DESC")
    List<SpendLimit> findAllOrderedByDatetimeDesc();

    /**
     * Находит последний установленный лимит для указанной категории расходов.
     * <p>
     * Возвращает самый свежий лимит по времени установки для конкретной категории.
     * Пример использования:
     * <pre>{@code
     * Optional<SpendLimit> limit = repository.findTopByExpenseCategoryOrderByDatetimeDesc(ExpenseCategory.PRODUCT);
     * }</pre>
     *
     * @param expenseCategory категория расходов для поиска (не может быть null)
     * @return {@link Optional} с последним лимитом для категории,
     *         или {@link Optional#empty()} если лимиты отсутствуют
     */
    Optional<SpendLimit> findTopByExpenseCategoryOrderByDatetimeDesc(ExpenseCategory expenseCategory);
}
