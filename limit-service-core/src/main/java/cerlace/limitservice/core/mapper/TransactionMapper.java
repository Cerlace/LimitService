package cerlace.limitservice.core.mapper;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.persistence.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "spendLimit.usdSum", target = "limitSum")
    @Mapping(source = "spendLimit.datetime", target = "limitDatetime")
    TransactionResponse toDto(Transaction entity);

    Transaction toEntity(TransactionCreateRequest entity);

    List<TransactionResponse> toDtoList(List<Transaction> entityList);
}
