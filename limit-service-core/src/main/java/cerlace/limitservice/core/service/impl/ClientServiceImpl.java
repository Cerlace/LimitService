package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.dto.SpendLimitCreateRequest;
import cerlace.limitservice.core.dto.SpendLimitResponse;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.mapper.SpendLimitMapper;
import cerlace.limitservice.core.mapper.TransactionMapper;
import cerlace.limitservice.core.service.ClientService;
import cerlace.limitservice.persistence.repository.SpendLimitRepository;
import cerlace.limitservice.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final SpendLimitRepository spendLimitRepository;
    private final TransactionRepository transactionRepository;

    private final SpendLimitMapper spendLimitMapper;
    private final TransactionMapper transactionMapper;

    @Transactional
    @Override
    public SpendLimitResponse setSpendLimit(SpendLimitCreateRequest request) {
        return spendLimitMapper.toDto(
                spendLimitRepository.save(spendLimitMapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SpendLimitResponse> getAllSpendLimits() {
        return spendLimitMapper.toDtoList(spendLimitRepository.findAllOrderedByDatetimeDesc());
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionResponse> getLimitExceededTransactions() {
        return transactionMapper.toDtoList(transactionRepository.findByLimitExceededTrueOrdered());
    }
}
