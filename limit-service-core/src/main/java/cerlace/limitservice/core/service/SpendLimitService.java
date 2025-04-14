package cerlace.limitservice.core.service;

import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;

public interface SpendLimitService {
    SpendLimit getCurrentSpendLimit(ExpenseCategory expenseCategory);
}
