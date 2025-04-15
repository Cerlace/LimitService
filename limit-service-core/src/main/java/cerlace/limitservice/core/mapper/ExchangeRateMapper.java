package cerlace.limitservice.core.mapper;

import cerlace.limitservice.core.dto.ExchangeRateResponse;
import cerlace.limitservice.core.utils.MappingUtils;
import cerlace.limitservice.persistence.entity.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Маппер для преобразования объектов курсов валют между слоями приложения.
 * <p>
 * Использует MapStruct для автоматического маппинга DTO и сущностей.
 * </p>
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExchangeRateMapper {
    ExchangeRateMapper INSTANCE = Mappers.getMapper(ExchangeRateMapper.class);

    /**
     * Преобразует DTO {@link ExchangeRateResponse} в сущность {@link ExchangeRate}.
     *
     * @param dto объект ответа с данными о курсе валют
     * @return сущность {@link ExchangeRate}
     */
    @Mapping(source = "currencyPair", target = "currencyShortname", qualifiedByName = "pairToShortname")
    ExchangeRate toEntity(ExchangeRateResponse dto);

    /**
     * Преобразует валютную пару в короткое наименование валюты.
     *
     * @param currencyPair строка с валютной парой (например, "RUB/USD")
     * @return короткое наименование валюты
     */
    @Named("pairToShortname")
    static String pairToShortname(String currencyPair) {
        return MappingUtils.currencyPairToShortname(currencyPair);
    }
}
