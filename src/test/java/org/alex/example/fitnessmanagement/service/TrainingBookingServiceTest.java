package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.BookingResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateBookingDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTrainingBooking;
import org.alex.example.fitnessmanagement.entity.TrainingBooking;
import org.alex.example.fitnessmanagement.mapper.TrainingBookingMapper;
import org.alex.example.fitnessmanagement.repository.TrainingBookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingBookingServiceTest {

    @Mock
    private TrainingBookingRepository trainingBookingRepository;

    @Mock
    private TrainingBookingMapper trainingBookingMapper;

    @InjectMocks
    private TrainingBookingServiceImpl trainingBookingService;


    @Test
    public void addTrainingBookingTest() {
        long clientId = 1L;
        long trainingSessionId = 1L;

        CreateBookingDto createBookingDto = new CreateBookingDto();
        createBookingDto.setClientId(clientId);
        createBookingDto.setTrainingSessionId(trainingSessionId);

        TrainingBooking trainingBooking = new TrainingBooking();
        trainingBooking.setId(1L);

        when(trainingBookingMapper.toEntity(createBookingDto)).thenReturn(trainingBooking);
        when(trainingBookingRepository.save(trainingBooking)).thenReturn(trainingBooking);

        trainingBookingService.add(createBookingDto);

        verify(trainingBookingMapper, times(1)).toEntity(createBookingDto);
        verify(trainingBookingRepository, times(1)).save(trainingBooking);
    }

    @Test
    public void findBookingByIdTest() {
        long bookingId = 1L;

        TrainingBooking trainingBooking = new TrainingBooking();
        trainingBooking.setId(bookingId);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(bookingId);

        when(trainingBookingMapper.toDto(trainingBooking)).thenReturn(bookingResponseDto);
        when(trainingBookingRepository.findById(bookingId)).thenReturn(Optional.of(trainingBooking));

        trainingBookingService.findBookingById(bookingId);

        verify(trainingBookingMapper, times(1)).toDto(trainingBooking);
        verify(trainingBookingRepository, times(1)).findById(bookingId);
    }

    @Test
    public void findBookingByClientIdTest() {
        long clientId = 1L;

        TrainingBooking trainingBooking = new TrainingBooking();
        trainingBooking.setId(1L);

        TrainingBooking trainingBooking2 = new TrainingBooking();
        trainingBooking2.setId(2L);

        List<TrainingBooking> trainingBookingList = new ArrayList<>();
        trainingBookingList.add(trainingBooking);
        trainingBookingList.add(trainingBooking2);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(1L);

        BookingResponseDto bookingResponseDto2 = new BookingResponseDto();
        bookingResponseDto2.setId(2L);

        List<BookingResponseDto> bookingResponseDtoList = new ArrayList<>();
        bookingResponseDtoList.add(bookingResponseDto);
        bookingResponseDtoList.add(bookingResponseDto2);

        when(trainingBookingMapper.toDtoList(trainingBookingList)).thenReturn(bookingResponseDtoList);
        when(trainingBookingRepository.findByClientId(clientId)).thenReturn(trainingBookingList);

        trainingBookingService.findByClientId(clientId);

        verify(trainingBookingMapper, times(1)).toDtoList(trainingBookingList);
        verify(trainingBookingRepository, times(1)).findByClientId(clientId);
    }

    @Test
    public void findBookingByTrainingSessionIdTest() {
        long trainingSessionId = 1L;

        TrainingBooking trainingBooking = new TrainingBooking();
        trainingBooking.setId(1L);

        TrainingBooking trainingBooking2 = new TrainingBooking();
        trainingBooking2.setId(2L);

        List<TrainingBooking> trainingBookingList = new ArrayList<>();
        trainingBookingList.add(trainingBooking);
        trainingBookingList.add(trainingBooking2);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(1L);

        BookingResponseDto bookingResponseDto2 = new BookingResponseDto();
        bookingResponseDto2.setId(2L);

        List<BookingResponseDto> bookingResponseDtoList = new ArrayList<>();
        bookingResponseDtoList.add(bookingResponseDto);
        bookingResponseDtoList.add(bookingResponseDto2);

        when(trainingBookingMapper.toDtoList(trainingBookingList)).thenReturn(bookingResponseDtoList);
        when(trainingBookingRepository.findByTrainingSessionId(trainingSessionId)).thenReturn(trainingBookingList);

        trainingBookingService.findByTrainingSessionId(trainingSessionId);

        verify(trainingBookingMapper, times(1)).toDtoList(trainingBookingList);
        verify(trainingBookingRepository, times(1)).findByTrainingSessionId(trainingSessionId);
    }


    @Test
    public void findByClientIdAndTrainingSessionIdTest() {
        long clientId = 1L;
        long trainingSessionId = 1L;

        TrainingBooking trainingBooking = new TrainingBooking();
        trainingBooking.setId(1L);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(1L);

        when(trainingBookingMapper.toDto(trainingBooking)).thenReturn(bookingResponseDto);
        when(trainingBookingRepository.findByClientIdAndTrainingSessionId(clientId, trainingSessionId)).thenReturn(Optional.of(trainingBooking));

        trainingBookingService.findByClientIdAndTrainingSessionId(clientId, trainingSessionId);

        verify(trainingBookingMapper, times(1)).toDto(trainingBooking);
        verify(trainingBookingRepository, times(1)).findByClientIdAndTrainingSessionId(clientId, trainingSessionId);
    }

    @Test
    public void findBookingByStatusTest() {
        StatusOfTrainingBooking status = StatusOfTrainingBooking.BOOKED;

        TrainingBooking trainingBooking = new TrainingBooking();
        trainingBooking.setId(1L);
        trainingBooking.setStatus(status);

        TrainingBooking trainingBooking2 = new TrainingBooking();
        trainingBooking2.setId(2L);
        trainingBooking2.setStatus(status);

        List<TrainingBooking> trainingBookingList = new ArrayList<>();
        trainingBookingList.add(trainingBooking);
        trainingBookingList.add(trainingBooking2);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(1L);
        bookingResponseDto.setStatus(status);

        BookingResponseDto bookingResponseDto2 = new BookingResponseDto();
        bookingResponseDto2.setId(2L);
        bookingResponseDto2.setStatus(status);

        List<BookingResponseDto> bookingResponseDtoList = new ArrayList<>();
        bookingResponseDtoList.add(bookingResponseDto);
        bookingResponseDtoList.add(bookingResponseDto2);

        when(trainingBookingMapper.toDtoList(trainingBookingList)).thenReturn(bookingResponseDtoList);
        when(trainingBookingRepository.findByStatus(status)).thenReturn(trainingBookingList);

        trainingBookingService.findByStatus(status);

        verify(trainingBookingMapper, times(1)).toDtoList(trainingBookingList);
        verify(trainingBookingRepository, times(1)).findByStatus(status);
    }

    @Test
    public void findAllBookingsTest() {
        TrainingBooking trainingBooking = new TrainingBooking();
        trainingBooking.setId(1L);

        TrainingBooking trainingBooking2 = new TrainingBooking();
        trainingBooking2.setId(2L);

        List<TrainingBooking> trainingBookingList = new ArrayList<>();
        trainingBookingList.add(trainingBooking);
        trainingBookingList.add(trainingBooking2);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(1L);

        BookingResponseDto bookingResponseDto2 = new BookingResponseDto();
        bookingResponseDto2.setId(2L);

        List<BookingResponseDto> bookingResponseDtoList = new ArrayList<>();
        bookingResponseDtoList.add(bookingResponseDto);
        bookingResponseDtoList.add(bookingResponseDto2);

        when(trainingBookingMapper.toDtoList(trainingBookingList)).thenReturn(bookingResponseDtoList);
        when(trainingBookingRepository.findAll()).thenReturn(trainingBookingList);

        trainingBookingService.showAll();

        verify(trainingBookingMapper, times(1)).toDtoList(trainingBookingList);
        verify(trainingBookingRepository, times(1)).findAll();
    }

    @Test
    public void cancelTest() {
        Long bookingId = 1L;

        TrainingBooking booking = new TrainingBooking();
        booking.setId(bookingId);

        when(trainingBookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        trainingBookingService.cancel(bookingId);

        assertEquals(StatusOfTrainingBooking.CANCELLED, booking.getStatus());
        verify(trainingBookingRepository).findById(bookingId);
        verify(trainingBookingRepository).save(booking);
    }

    @Test
    public void markAttendedTest() {
        Long bookingId = 1L;

        TrainingBooking booking = new TrainingBooking();
        booking.setId(bookingId);

        when(trainingBookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        trainingBookingService.markAttended(bookingId);

        assertTrue(booking.isAttended());
        assertEquals(StatusOfTrainingBooking.ATTENDED, booking.getStatus());
        verify(trainingBookingRepository).findById(bookingId);
        verify(trainingBookingRepository).save(booking);
    }

    @Test
    public void markNoShowTest() {
        Long bookingId = 1L;

        TrainingBooking booking = new TrainingBooking();
        booking.setId(bookingId);

        when(trainingBookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        trainingBookingService.markNoShow(bookingId);

        assertFalse(booking.isAttended());
        assertEquals(StatusOfTrainingBooking.NO_SHOW, booking.getStatus());
        verify(trainingBookingRepository).findById(bookingId);
        verify(trainingBookingRepository).save(booking);
    }

    @Test
    public void deleteBookingTest() {
        long bookingId = 1L;

        TrainingBooking trainingBooking = new TrainingBooking();
        trainingBooking.setId(bookingId);

        when(trainingBookingRepository.findById(bookingId)).thenReturn(Optional.of(trainingBooking));

        trainingBookingService.delete(bookingId);

        verify(trainingBookingRepository, times(1)).findById(bookingId);
        verify(trainingBookingRepository, times(1)).delete(trainingBooking);
    }
}
