package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.response.HotelSimpleResponseDto;
import org.example.entity.Hotel;
import org.example.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<HotelSimpleResponseDto> getAllHotelsDto() {
        List<Hotel> hotels = hotelRepository.findAll();
        return null;
    }

}
