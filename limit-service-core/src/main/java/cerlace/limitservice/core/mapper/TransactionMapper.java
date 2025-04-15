package cerlace.limitservice.core.mapper;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.persistence.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Маппер для преобразования транзакций между слоями приложения.
 * <p>
 * Использует MapStruct для автоматического маппинга DTO и сущностей.
 * </p>
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    /**
     * Преобразует сущность {@link Transaction} в DTO {@link TransactionResponse}.
     * <p>
     * Также преобразует поля лимита расходов в соответствующие поля DTO.
     * </p>
     *
     * @param entity сущность транзакции
     * @return DTO транзакции
     */
    @Mapping(source = "spendLimit.usdSum", target = "limitSum")
    @Mapping(source = "spendLimit.datetime", target = "limitDatetime")
    TransactionResponse toDto(Transaction entity);

    /**
     * Преобразует DTO запроса на создание транзакции в сущность {@link Transaction}.
     *
     * @param entity DTO запроса на создание транзакции
     * @return сущность транзакции
     */
    Transaction toEntity(TransactionCreateRequest entity);

    /**
     * Преобразует список сущностей транзакций в список DTO.
     *
     * @param entityList список сущностей транзакций
     * @return список DTO транзакций
     */
    List<TransactionResponse> toDtoList(List<Transaction> entityList);
}
