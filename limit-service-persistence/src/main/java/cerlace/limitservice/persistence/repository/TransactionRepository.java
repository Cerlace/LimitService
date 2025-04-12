package cerlace.limitservice.persistence.repository;

import cerlace.limitservice.persistence.entity.Transaction;
import cerlace.limitservice.persistence.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT SUM(t.usdSum) FROM Transaction t WHERE t.expenseCategory = :category " +
            "AND t.datetime BETWEEN :from AND :to")
    Optional<BigDecimal> sumByCategoryAndDatetime(@Param("category") ExpenseCategory category,
                                                  @Param("from") OffsetDateTime from,
                                                  @Param("to") OffsetDateTime to);

    @Query("SELECT t, sl FROM Transaction t JOIN SpendLimit sl ON t.spendLimit.id = sl.id " +
            "WHERE t.expenseCategory = :category AND t.limitExceeded = true ")
    List<Transaction> findByCategoryAndLimitExceededTrue(@Param("category") ExpenseCategory category);
}
