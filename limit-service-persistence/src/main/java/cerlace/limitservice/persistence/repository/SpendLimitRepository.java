package cerlace.limitservice.persistence.repository;

import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpendLimitRepository extends JpaRepository<SpendLimit, UUID> {
    Optional<SpendLimit> findTopByExpenseCategoryOrderByDatetimeDesc(ExpenseCategory expenseCategory);
}
