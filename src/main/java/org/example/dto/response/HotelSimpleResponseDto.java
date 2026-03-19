package org.example.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HotelSimpleResponseDto {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;

}
