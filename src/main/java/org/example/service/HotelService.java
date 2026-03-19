package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.response.HotelFullResponseDto;
import org.example.dto.response.HotelSimpleResponseDto;
import org.example.entity.Hotel;
import org.example.mapper.AddressMapper;
import org.example.mapper.ArrivalTimeMapper;
import org.example.mapper.ContactMapper;
import org.example.mapper.HotelMapper;
import org.example.repository.HotelRepository;
import org.example.repository.specification.HotelSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final AddressMapper addressMapper;
    private final ArrivalTimeMapper arrivalTimeMapper;
    private final ContactMapper contactMapper;

    public List<HotelSimpleResponseDto> getAllHotelsDto() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(hotelMapper::hotelToHotelSimpleResponseDto)
                .toList();
    }

    public HotelFullResponseDto getHotelDtoById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new EntityNotFoundException("Отель не найден"));
        HotelFullResponseDto hotelFullResponseDto = hotelMapper.hotelToHotelFullResponseDto(hotel);
        hotelFullResponseDto.setAddress(addressMapper.toAddressResponseDto(hotel.getAddress()));
        hotelFullResponseDto.setArrivalTime(arrivalTimeMapper.toArrivalTimeResponseDto(hotel.getArrivalTime()));
        hotelFullResponseDto.setContacts(contactMapper.toContactResponseDto(hotel.getContacts()));
        return hotelFullResponseDto;
    }

    public List<HotelSimpleResponseDto> searchAllHotelsDto(String amenities, String brand, String city, String name) {
        Specification<Hotel> spec = Specification.where(HotelSpecification.hasAmenities(amenities).or(HotelSpecification.
                hasBrand(brand)).or(HotelSpecification.hasCity(city)).or(HotelSpecification.hasName(name)));
        List<Hotel> hotels = hotelRepository.findAll(spec);
        return hotels.stream().map(hotelMapper::hotelToHotelSimpleResponseDto).toList();
    }

    public HotelSimpleResponseDto createHotel(Hotel hotel) {
        return null;
    }

}
