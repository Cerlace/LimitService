package cerlace.limitservice.core.mapper;

import cerlace.limitservice.core.dto.SpendLimitCreateRequest;
import cerlace.limitservice.core.dto.SpendLimitResponse;
import cerlace.limitservice.persistence.entity.SpendLimit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpendLimitMapper {
    SpendLimitMapper INSTANCE = Mappers.getMapper(SpendLimitMapper.class);

    SpendLimitResponse toDto(SpendLimit entity);

    SpendLimit toEntity(SpendLimitCreateRequest entity);

    List<SpendLimitResponse> toDtoList(List<SpendLimit> entityList);
}
