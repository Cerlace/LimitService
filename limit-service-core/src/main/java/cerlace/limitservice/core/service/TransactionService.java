package cerlace.limitservice.core.service;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;

public interface TransactionService {
    TransactionResponse saveTransaction(TransactionCreateRequest request);
}
