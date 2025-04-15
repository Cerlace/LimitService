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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SpendLimitResponse {

    @Schema(description = "Сумма лимита", example = "1000.00")
    @JsonProperty("usd_sum")
    private BigDecimal usdSum;

    @Schema(description = "Категория расходов")
    @JsonProperty("expense_category")
    private ExpenseCategory expenseCategory;

    @Schema(description = "Дата лимита",
            example = "2022-01-10 00:00:00+06",
            pattern = "yyyy-MM-dd HH:mm:ssx")
    @JsonProperty("datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssx")
    private OffsetDateTime datetime;
}
