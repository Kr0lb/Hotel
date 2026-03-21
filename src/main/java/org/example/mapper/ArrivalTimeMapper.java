package org.example.mapper;

import org.example.dto.ArrivalTimeDto;
import org.example.entity.ArrivalTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArrivalTimeMapper {

    ArrivalTimeDto toArrivalTimeResponseDto(ArrivalTime arrivalTime);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    ArrivalTime arrivalTimeDtoToArrivalTime(ArrivalTimeDto arrivalTimeDto);

}
