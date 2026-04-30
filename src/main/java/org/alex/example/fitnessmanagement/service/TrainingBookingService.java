package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.BookingResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateBookingDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTrainingBooking;

import java.util.List;

public interface TrainingBookingService {

    void add(CreateBookingDto createBookingDto);

    BookingResponseDto findBookingById(long id);

    List<BookingResponseDto> findByClientId(Long clientId);

    List<BookingResponseDto> findByTrainingSessionId(Long trainingSessionId);

    BookingResponseDto findByClientIdAndTrainingSessionId(Long clientId, Long trainingSessionId);

    List<BookingResponseDto> findByStatus(StatusOfTrainingBooking status);

    List<BookingResponseDto> showAll();

    void cancel(Long bookingId);

    void markAttended(Long bookingId);

    void markNoShow(Long bookingId);

    void delete(Long bookingId);
}
