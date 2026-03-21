package org.example.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.dto.AddressDto;
import org.example.dto.ArrivalTimeDto;
import org.example.dto.ContactDto;

@Getter
@Setter
@Builder
public class HotelRequestDto {

    @Pattern(regexp = "^[\\w 0-9]+$")
    @Schema(description = "Название отеля", example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "Описание отеля", example = """
            The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...""")
    private String description;

    @Pattern(regexp = "^[A-z0-9 ]*$")
    @Schema(description = "Бренд", example = "Hilton")
    private String brand;

    @NotNull
    private AddressDto address;

    @NotNull
    private ContactDto contacts;

    @NotNull
    private ArrivalTimeDto arrivalTime;

}
