package cerlace.limitservice.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static cerlace.limitservice.persistence.Constants.CURRENCY_SHORTNAME_LENGTH;

/**
 * Сущность, представляющая курс обмена валюты на определенную дату.
 * <p>
 * Хранит информацию о курсе валюты по отношению к базовой валюте системы (USD)
 * на конкретную дату. Обеспечивает уникальность комбинации валюты и даты.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "exchange_rate", uniqueConstraints =
@UniqueConstraint(columnNames = {"currency_shortname", "date"}))
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "currency_shortname", length = CURRENCY_SHORTNAME_LENGTH, nullable = false)
    private String currencyShortname;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}
