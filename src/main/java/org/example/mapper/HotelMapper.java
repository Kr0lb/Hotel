package org.example.mapper;

import org.example.dto.request.HotelRequestDto;
import org.example.dto.response.HotelFullResponseDto;
import org.example.dto.response.HotelSimpleResponseDto;
import org.example.entity.Address;
import org.example.entity.Amenities;
import org.example.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                AddressMapper.class,
                ContactMapper.class,
                ArrivalTimeMapper.class
        })
public interface HotelMapper {

    @Mapping(target = "phone", source = "contacts.phone")
    @Mapping(target = "address", expression = "java(addressToString(hotel.getAddress()))")
    HotelSimpleResponseDto hotelToHotelSimpleResponseDto(Hotel hotel);

    @Mapping(target = "amenities", expression = "java(amenitiesToList(hotel.getAmenities()))")
    HotelFullResponseDto hotelToHotelFullResponseDto(Hotel hotel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    Hotel hotelRequestDtoToHotel(HotelRequestDto hotelRequestDto);

    default Set<String> amenitiesToList(Set<Amenities> amenities) {
        return amenities.stream().map(Amenities::getName).collect(Collectors.toSet());
    }

    default String addressToString(Address address) {
        return "%s %s, %s, %s, %s".formatted(address.getHouseNumber(), address.getStreet(), address.getCountry(),
                address.getPostCode(), address.getCity());
    }

}
