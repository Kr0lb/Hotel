package org.example.mapper;

import org.example.dto.AddressDto;
import org.example.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {

    AddressDto toAddressResponseDto(Address address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    Address addressDtoToAddress(AddressDto addressDto);

}
