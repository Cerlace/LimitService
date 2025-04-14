package cerlace.limitservice.core.service;

import cerlace.limitservice.core.dto.SpendLimitCreateRequest;
import cerlace.limitservice.core.dto.SpendLimitResponse;
import cerlace.limitservice.core.dto.TransactionResponse;

import java.util.List;

public interface ClientService {
    SpendLimitResponse setSpendLimit(SpendLimitCreateRequest request);
    List<SpendLimitResponse> getAllSpendLimits();
    List<TransactionResponse> getLimitExceededTransactions();
}
