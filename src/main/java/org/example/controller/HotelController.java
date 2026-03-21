package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.example.dto.request.HotelRequestDto;
import org.example.dto.response.HotelFullResponseDto;
import org.example.dto.response.HotelSimpleResponseDto;
import org.example.exception.handler.ErrorResponse;

import java.util.List;
import java.util.Map;

@Tag(name = "Hotel API", description = "Управление отелями")
public interface HotelController {

    @Operation(summary = "Получение списка всех отелей с их краткой информацией",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список отелей успешно получен",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = HotelSimpleResponseDto.class))
                            )
                    )
            }
    )
    List<HotelSimpleResponseDto> getHotels();

    @Operation(summary = "Получение расширенной информации по конкретному отелю",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отель успешно получен",
                            content = @Content(schema = @Schema(implementation = HotelFullResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Отель не найден",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = """
                                                    {
                                                        "code": 400,
                                                        "message": "Отель не найден"
                                                    }"""
                                    )
                            )
                    )
            }
    )
    HotelFullResponseDto getHotelById(Long id);

    @Operation(summary = "Получение списка всех отелей с их краткой информацией по следующим параметрам: name, brand, city, country, amenities.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отели успешно получен",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = HotelSimpleResponseDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Неверные параметры запроса",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = """
                                                    {
                                                        "code": 400,
                                                        "message": "Неверные параметры запроса"
                                                    }"""
                                    )
                            )
                    )
            }
    )
    List<HotelSimpleResponseDto> getHotelBySearch(
            String name,
            String brand,
            String city,
            String country,
            String amenities);

    @Operation(summary = "Создание отеля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отели успешно сохранен",
                            content = @Content(schema = @Schema(implementation = HotelSimpleResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Ошибка валидации",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = """
                                                    {
                                                        "code": 400,
                                                        "message": "Ошибка валидации"
                                                    }"""
                                    )
                            )
                    )
            }
    )
    HotelSimpleResponseDto createHotel(@Valid HotelRequestDto hotelRequestDto);

    @Operation(summary = "Добавление списка amenities к отелю",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удобства успешно добавлены"
                    )
            }
    )
    void addAmenities(List<String> amenities, Long id);

    @Operation(summary = "Получение количества отелей сгруппированных по каждому значению указанного параметра. Параметр: brand, city, country, amenities.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Гистограмма получена",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class),
                                    examples = @ExampleObject(
                                            value = """
                                                    {
                                                        "Minsk": 1,
                                                        "Moscow": 2,
                                                        "Mogilev": 1
                                                    }"""
                                    )
                            )
                    )
            }
    )
    Map<String, Long> getHistogram(
            @Pattern(regexp = "^(brand|city|country|amenities)$", message = "Запрос может включать только одно из значений: brand, city, country, amenities.")
            String param);

}
