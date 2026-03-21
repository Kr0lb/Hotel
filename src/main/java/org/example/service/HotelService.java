package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.HotelRequestDto;
import org.example.dto.response.HotelFullResponseDto;
import org.example.dto.response.HotelSimpleResponseDto;
import org.example.entity.Amenities;
import org.example.entity.Hotel;
import org.example.mapper.HotelMapper;
import org.example.repository.AmenitiesRepository;
import org.example.repository.HotelRepository;
import org.example.repository.specification.HotelSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final AmenitiesRepository amenitiesRepository;
    private final EntityManager entityManager;

    public List<HotelSimpleResponseDto> getHotelsDto() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(hotelMapper::hotelToHotelSimpleResponseDto)
                .toList();
    }

    public HotelFullResponseDto getHotelDtoById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new EntityNotFoundException("Отель не найден"));
        return hotelMapper.hotelToHotelFullResponseDto(hotel);
    }

    public List<HotelSimpleResponseDto> searchHotelsDto(String name, String brand, String city, String country, String amenities) {
        Specification<Hotel> spec = Specification.where(
                HotelSpecification.hasName(name)
                        .or(HotelSpecification.hasBrand(brand))
                        .or(HotelSpecification.hasCity(city))
                        .or(HotelSpecification.hasCountry(country))
                        .or(HotelSpecification.hasAmenities(amenities)));
        List<Hotel> hotels = hotelRepository.findAll(spec);
        return hotels.stream().map(hotelMapper::hotelToHotelSimpleResponseDto).toList();
    }

    public HotelSimpleResponseDto createHotel(HotelRequestDto hotelRequestDto) {
        Hotel hotel = hotelMapper.hotelRequestDtoToHotel(hotelRequestDto);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.hotelToHotelSimpleResponseDto(hotel);
    }

    public void addAmenitiesToHotel(Long hotelId, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new EntityNotFoundException("Отель не найден"));
        for (String amenity : amenities) {
            amenitiesRepository.save(Amenities.builder().hotel(hotel).name(amenity).build());
        }
    }

    public Map<String, Long> getHistogram(String param) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Hotel> root = query.from(Hotel.class);

        Expression<?> groupingField;
        if ("city".equals(param) || "country".equals(param)) {
            groupingField = root.get("address").get(param);
        } else if ("amenities".equals(param)) {
            groupingField = root.join("amenities").get("name");
        } else {
            groupingField = root.get(param);
        }

        query.multiselect(groupingField, cb.count(root));
        query.groupBy(groupingField);

        List<Object[]> results = entityManager.createQuery(query).getResultList();

        return results.stream()
                .collect(Collectors.toMap(
                        row -> String.valueOf(row[0]),
                        row -> (Long) row[1]
                ));
    }

}
