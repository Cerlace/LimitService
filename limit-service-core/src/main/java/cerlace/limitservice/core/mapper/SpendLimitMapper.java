package cerlace.limitservice.core.mapper;

import cerlace.limitservice.core.dto.SpendLimitCreateRequest;
import cerlace.limitservice.core.dto.SpendLimitResponse;
import cerlace.limitservice.persistence.entity.SpendLimit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Маппер для преобразования лимитов расходов между слоями приложения.
 * <p>
 * Использует MapStruct для автоматического маппинга DTO и сущностей.
 * </p>
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpendLimitMapper {

    SpendLimitMapper INSTANCE = Mappers.getMapper(SpendLimitMapper.class);

    /**
     * Преобразует сущность {@link SpendLimit} в DTO {@link SpendLimitResponse}.
     *
     * @param entity сущность лимита расходов
     * @return DTO лимита расходов
     */
    SpendLimitResponse toDto(SpendLimit entity);

    /**
     * Преобразует DTO запроса на создание лимита расходов в сущность {@link SpendLimit}.
     *
     * @param entity DTO запроса на создание лимита расходов
     * @return сущность лимита расходов
     */
    SpendLimit toEntity(SpendLimitCreateRequest entity);

    /**
     * Преобразует список сущностей лимитов расходов в список DTO.
     *
     * @param entityList список сущностей лимитов расходов
     * @return список DTO лимитов расходов
     */
    List<SpendLimitResponse> toDtoList(List<SpendLimit> entityList);
}
