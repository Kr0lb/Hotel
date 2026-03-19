package org.example.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponseDto {

    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;

}
