package cerlace.limitservice.core.dto;

import cerlace.limitservice.persistence.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * DTO для создания нового лимита расходов.
 * <p>
 * Содержит данные, необходимые для установки лимита расходов по конкретной категории.
 * Используется как входной параметр в API при создании лимита.
 *
 * @see SpendLimitResponse
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SpendLimitCreateRequest {

    @Schema(description = "Сумма лимита", example = "1000.00")
    @JsonProperty("usd_sum")
    private BigDecimal usdSum;

    @Schema(description = "Категория расходов")
    @JsonProperty("expense_category")
    private ExpenseCategory expenseCategory;
}
