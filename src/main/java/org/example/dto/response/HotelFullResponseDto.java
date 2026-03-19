package org.example.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class HotelFullResponseDto {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private AddressResponseDto address;
    private ContactResponseDto contacts;
    private ArrivalTimeResponseDto arrivalTime;
    private Set<String> amenities;

}
