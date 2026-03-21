package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Контакты отеля")
public class ContactDto {

    @Pattern(regexp = "\\+375 \\d \\d{3}-\\d{2}-\\d{2}")
    @Schema(description = "Телефон", example = "+375 17 309-80-00")
    private String phone;

    @Email
    @Schema(description = "Почта", example = "doubletreeminsk.info@hilton.com")
    private String email;

}
