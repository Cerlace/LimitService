package cerlace.limitservice.core.dto;

import cerlace.limitservice.core.utils.Constants;
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
public class TransactionResponse {
    @JsonProperty("account_from")
    private Long accountFrom;
    @JsonProperty("account_to")
    private Long accountTo;
    @JsonProperty("currency_shortname")
    private String currencyShortname;
    @JsonProperty("sum")
    private BigDecimal sum;
    @JsonProperty("expense_category")
    private ExpenseCategory expenseCategory;
    @JsonProperty("datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssx")
    private OffsetDateTime datetime;
    @JsonProperty("limit_sum")
    private BigDecimal limitSum;
    @JsonProperty("limit_datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssx")
    private OffsetDateTime limitDatetime;
    @Builder.Default
    @JsonProperty("limit_currency_shortname")
    private String limitCurrencyShortname = Constants.USD_SHORTNAME;
}
