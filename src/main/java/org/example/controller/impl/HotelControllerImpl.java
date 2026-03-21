package org.example.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.controller.HotelController;
import org.example.dto.request.HotelRequestDto;
import org.example.dto.response.HotelFullResponseDto;
import org.example.dto.response.HotelSimpleResponseDto;
import org.example.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view")
public class HotelControllerImpl implements HotelController {

    private final HotelService hotelService;


    @Override
    @GetMapping("/hotels")
    public List<HotelSimpleResponseDto> getHotels() {
        return hotelService.getHotelsDto();
    }

    @Override
    @GetMapping("/hotels/{id}")
    public HotelFullResponseDto getHotelById(@PathVariable("id") Long id) {
        return hotelService.getHotelDtoById(id);
    }

    @Override
    @GetMapping("/search")
    public List<HotelSimpleResponseDto> getHotelBySearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenities
    ) {
        return hotelService.searchHotelsDto(name, brand, city, country, amenities);
    }

    @Override
    @PostMapping("/hotels")
    public HotelSimpleResponseDto createHotel(@RequestBody @Valid HotelRequestDto hotelRequestDto) {
        return hotelService.createHotel(hotelRequestDto);
    }

    @Override
    @PostMapping("/hotels/{id}/amenities")
    public void addAmenities(@RequestBody List<String> amenities, @PathVariable("id") Long id) {
        hotelService.addAmenitiesToHotel(id, amenities);
    }

    @Override
    @GetMapping("/histogram/{param}")
    public Map<String, Long> getHistogram(@PathVariable("param") String param) {
        return hotelService.getHistogram(param);
    }
}
