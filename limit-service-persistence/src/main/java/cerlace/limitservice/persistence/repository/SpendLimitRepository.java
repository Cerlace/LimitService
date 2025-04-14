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

@Repository
public interface SpendLimitRepository extends JpaRepository<SpendLimit, UUID> {
    @Transactional(readOnly = true)
    @Query("SELECT s FROM SpendLimit s ORDER BY s.datetime DESC")
    List<SpendLimit> findAllOrderedByDatetimeDesc();
    Optional<SpendLimit> findTopByExpenseCategoryOrderByDatetimeDesc(ExpenseCategory expenseCategory);
}
