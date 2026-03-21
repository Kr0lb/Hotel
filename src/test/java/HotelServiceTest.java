import jakarta.persistence.EntityNotFoundException;
import org.example.dto.request.HotelRequestDto;
import org.example.dto.response.HotelFullResponseDto;
import org.example.dto.response.HotelSimpleResponseDto;
import org.example.entity.Hotel;
import org.example.mapper.AddressMapper;
import org.example.mapper.ArrivalTimeMapper;
import org.example.mapper.ContactMapper;
import org.example.mapper.HotelMapper;
import org.example.repository.HotelRepository;
import org.example.service.HotelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private ArrivalTimeMapper arrivalTimeMapper;

    @Mock
    private ContactMapper contactMapper;

    @InjectMocks
    private HotelService hotelService;

    @Test
    @DisplayName("Получение отеля по Id - 200")
    void getHotelDtoById_Success() {
        Long hotelId = 1L;
        Hotel hotel = new Hotel();
        HotelFullResponseDto expectedDto = HotelFullResponseDto.builder().build();

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
        when(hotelMapper.hotelToHotelFullResponseDto(any())).thenReturn(expectedDto);

        HotelFullResponseDto result = hotelService.getHotelDtoById(hotelId);

        assertNotNull(result);
        verify(hotelRepository).findById(hotelId);
    }

    @Test
    @DisplayName("Получение отеля по Id - 400")
    void getHotelDtoById_NotFound() {
        when(hotelRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> hotelService.getHotelDtoById(1L));
    }

    @Test
    @DisplayName("Создание отеля - 200")
    void createHotel_Success() {
        HotelRequestDto request = HotelRequestDto.builder().build();
        Hotel hotel = new Hotel();

        when(hotelMapper.hotelRequestDtoToHotel(any())).thenReturn(hotel);
        when(hotelRepository.save(any())).thenReturn(hotel);
        when(hotelMapper.hotelToHotelSimpleResponseDto(any())).thenReturn(HotelSimpleResponseDto.builder().build());

        HotelSimpleResponseDto result = hotelService.createHotel(request);

        assertNotNull(result);
        verify(hotelRepository).save(any(Hotel.class));
    }
}

