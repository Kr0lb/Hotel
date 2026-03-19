package org.example.mapper;

import org.example.dto.response.HotelFullResponseDto;
import org.example.dto.response.HotelSimpleResponseDto;
import org.example.entity.Address;
import org.example.entity.Amenities;
import org.example.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelMapper {

    @Mapping(target = "phone", source = "contacts.phone")
    @Mapping(target = "address", expression = "java(addressToString(address))")
    HotelSimpleResponseDto hotelToHotelSimpleResponseDto(Hotel hotel);

    @Mapping(target = "amenities", expression = "java()")
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "arrivalTime", ignore = true)
    HotelFullResponseDto hotelToHotelFullResponseDto(Hotel hotel);

    default List<String> amenitiesToList(Set<Amenities> amenities) {
        return amenities.stream().map(Amenities::getName).toList();
    }

    default String addressToString(Address address) {
        return "%s %s, %s, %s, %s".formatted(address.getHouseNumber(), address.getStreet(), address.getCountry(),
                address.getPostCode(), address.getCity());
    }

}
