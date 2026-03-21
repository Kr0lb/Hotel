package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class ArrivalTimeDto {

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    @Schema(example = "14:00")
    private LocalTime checkIn;

    @JsonFormat(pattern = "HH:mm")
    @Schema(example = "12:00")
    private LocalTime checkOut;


}
