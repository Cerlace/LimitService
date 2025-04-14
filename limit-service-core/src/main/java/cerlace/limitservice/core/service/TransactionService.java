package cerlace.limitservice.core.service;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.persistence.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface TransactionService {
    TransactionResponse saveTransaction(TransactionCreateRequest request);

    BigDecimal getMonthTransactionsSum(ExpenseCategory category, OffsetDateTime dateTime);
}
