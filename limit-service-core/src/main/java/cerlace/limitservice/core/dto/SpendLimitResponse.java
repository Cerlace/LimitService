package cerlace.limitservice.core.dto;

import cerlace.limitservice.persistence.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("usd_sum")
    private BigDecimal usdSum;
    @JsonProperty("expense_category")
    private ExpenseCategory expenseCategory;
    @JsonProperty("datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssx")
    private OffsetDateTime datetime;
}
