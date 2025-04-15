package cerlace.limitservice.persistence.entity;

import cerlace.limitservice.persistence.enums.ExpenseCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static cerlace.limitservice.persistence.Constants.DECIMAL_SCALE;

/**
 * Сущность, представляющая лимит расходов для определенной категории.
 * <p>
 * Хранит информацию об установленном лимите расходов в USD для конкретной категории,
 * а также дату и время установки лимита. Используется для контроля расходов клиентов.
 *
 * @see Transaction
 * @see ExpenseCategory
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "spend_limit")
public class SpendLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "usd_sum", scale = DECIMAL_SCALE, nullable = false)
    private BigDecimal usdSum;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "expense_category", nullable = false)
    private ExpenseCategory expenseCategory;

    @CreationTimestamp
    @Column(name = "datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private OffsetDateTime datetime;
}
