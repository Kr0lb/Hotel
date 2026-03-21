package org.example.controller.impl;

import org.example.exception.handler.GlobalExceptionHandler;
import org.example.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HotelControllerImplTest {

    private MockMvc mockMvc;

    private final String BASE_URL = "/property-view%s";

    @Mock
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HotelControllerImpl(hotelService))
                .setControllerAdvice(GlobalExceptionHandler.class)

                .build();
    }

    @Test
    @DisplayName("Успешное получение отелей - 200")
    void getHotels() throws Exception {
        mockMvc.perform(get(BASE_URL.formatted("/hotels")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Успешное поиск отелей по параметрам - 200")
    void getHotelBySearch() throws Exception {
        mockMvc.perform(get(BASE_URL.formatted("/search?city=Minsk")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Ошибка при получении гистограммы - 400")
    void getHistogram() throws Exception {
        mockMvc.perform(get(BASE_URL.formatted("/histogram/city")))
                .andDo(print())
                .andExpect(status().isOk());
    }

}