package cerlace.limitservice.persistence.entity;

import cerlace.limitservice.persistence.enums.ExpenseCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static cerlace.limitservice.persistence.Constants.CURRENCY_SHORTNAME_LENGTH;
import static cerlace.limitservice.persistence.Constants.DECIMAL_SCALE;

/**
 * Сущность, представляющая транзакцию в системе.
 * <p>
 * Содержит информацию о денежном переводе между счетами, включая данные об отправителе,
 * получателе, сумме, валюте и категории расхода. Также хранит информацию о превышении лимитов.
 *
 * @see SpendLimit
 * @see ExpenseCategory
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_from", nullable = false)
    private Long accountFrom;

    @Column(name = "account_to", nullable = false)
    private Long accountTo;

    @Column(name = "currency_shortname", length = CURRENCY_SHORTNAME_LENGTH, nullable = false)
    private String currencyShortname;

    @Column(name = "sum", scale = DECIMAL_SCALE, nullable = false)
    private BigDecimal sum;

    @Column(name = "usd_sum", scale = DECIMAL_SCALE, nullable = false)
    private BigDecimal usdSum;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "expense_category", nullable = false)
    private ExpenseCategory expenseCategory;

    @Column(name = "datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE",
            nullable = false)
    private OffsetDateTime datetime;

    @Column(name = "limit_exceeded", nullable = false)
    private Boolean limitExceeded;

    @ManyToOne
    @JoinColumn(name = "spend_limit_id", nullable = false)
    private SpendLimit spendLimit;
}
