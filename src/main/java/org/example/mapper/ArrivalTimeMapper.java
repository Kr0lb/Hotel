package org.example.mapper;

import org.example.dto.response.ArrivalTimeResponseDto;
import org.example.entity.ArrivalTime;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArrivalTimeMapper {

    ArrivalTimeResponseDto toArrivalTimeResponseDto(ArrivalTime arrivalTime);

}
