package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Адрес отеля")
public class AddressDto {

    @Min(0)
    @Max(999)
    @Schema(description = "Номер дома", example = "9")
    private Short houseNumber;

    @Pattern(regexp = "^\\.+$")
    @Schema(description = "Улица", example = "Pobediteley Avenue")
    private String street;

    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Schema(description = "Город", example = "Minsk")
    private String city;

    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Schema(description = "Страна", example = "Belarus")
    private String country;


    @Pattern(regexp = "^[A-z0-9]+$")
    @Schema(description = "Почтовый индекс", example = "220004")
    private String postCode;

}
