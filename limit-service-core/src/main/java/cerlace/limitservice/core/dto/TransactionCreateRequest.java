package cerlace.limitservice.core.dto;

import cerlace.limitservice.persistence.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * DTO для запроса на создание транзакции.
 * <p>
 * Содержит все необходимые данные для создания транзакции.
 * Используется как входной формат данных в API при создании транзакции.
 *
 * @see TransactionResponse
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionCreateRequest {

    @Schema(description = "Номер аккаунта отправителя", example = "1234567890")
    @JsonProperty("account_from")
    private Long accountFrom;

    @Schema(description = "Номер аккаунта получателя", example = "9876543210")
    @JsonProperty("account_to")
    private Long accountTo;

    @Schema(description = "Код валюты", example = "EUR")
    @JsonProperty("currency_shortname")
    private String currencyShortname;

    @Schema(description = "Сумма транзакции", example = "1000.00")
    @JsonProperty("sum")
    private BigDecimal sum;

    @Schema(description = "Категория расходов")
    @JsonProperty("expense_category")
    private ExpenseCategory expenseCategory;

    @Schema(description = "Дата транзакции",
            example = "2022-01-10 00:00:00+06",
            pattern = "yyyy-MM-dd HH:mm:ssx")
    @JsonProperty("datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssx")
    private OffsetDateTime datetime;
}
