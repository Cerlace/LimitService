package cerlace.limitservice.core.mapper;

import cerlace.limitservice.core.dto.ExchangeRateResponse;
import cerlace.limitservice.core.utils.MappingUtils;
import cerlace.limitservice.persistence.entity.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExchangeRateMapper {
    ExchangeRateMapper INSTANCE = Mappers.getMapper(ExchangeRateMapper.class);

    @Mapping(source = "currencyPair", target = "currencyShortname", qualifiedByName = "pairToShortname")
    ExchangeRate toEntity(ExchangeRateResponse dto);

    @Named("pairToShortname")
    static String pairToShortname(String currencyPair) {
        return MappingUtils.currencyPairToShortname(currencyPair);
    }
}
