package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.service.SpendLimitService;
import cerlace.limitservice.core.utils.SpendLimitUtils;
import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;
import cerlace.limitservice.persistence.repository.SpendLimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendLimitServiceImpl implements SpendLimitService {

    private final SpendLimitRepository spendLimitRepository;

    @Override
    public SpendLimit getCurrentSpendLimit(ExpenseCategory expenseCategory) {
        return spendLimitRepository
                .findTopByExpenseCategoryOrderByDatetimeDesc(expenseCategory)
                .filter(SpendLimitUtils::isCurrentMonthLimit)
                .orElse(spendLimitRepository.save(SpendLimitUtils.getBaseSpendLimit(expenseCategory)));
    }
}
