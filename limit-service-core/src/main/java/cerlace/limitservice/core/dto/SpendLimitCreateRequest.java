package cerlace.limitservice.core.dto;

import cerlace.limitservice.persistence.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SpendLimitCreateRequest {
    @JsonProperty("usd_sum")
    private BigDecimal usdSum;
    @JsonProperty("expense_category")
    private ExpenseCategory expenseCategory;
}
