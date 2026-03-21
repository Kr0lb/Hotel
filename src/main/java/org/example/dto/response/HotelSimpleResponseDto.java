package org.example.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Краткая информация об отеле")
public class HotelSimpleResponseDto {

    @Schema(description = "Id", example = "1")
    private Long id;

    @Schema(description = "Название отеля", example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "Описание отеля", example = """
            The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...""")
    private String description;

    @Schema(description = "Адрес отеля", example = "9 Pobediteley Avenue, Minsk, 220004, Belarus")
    private String address;

    @Schema(description = "Телефон", example = "+375 17 309-80-00")
    private String phone;

}
