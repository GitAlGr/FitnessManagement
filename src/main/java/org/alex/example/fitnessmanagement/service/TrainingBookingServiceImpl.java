package org.alex.example.fitnessmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alex.example.fitnessmanagement.dto.BookingResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateBookingDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTrainingBooking;
import org.alex.example.fitnessmanagement.entity.TrainingBooking;
import org.alex.example.fitnessmanagement.error.NotFoundByIdException;
import org.alex.example.fitnessmanagement.mapper.TrainingBookingMapper;
import org.alex.example.fitnessmanagement.repository.TrainingBookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingBookingServiceImpl implements TrainingBookingService {

    private final TrainingBookingRepository trainingBookingRepository;
    private final TrainingBookingMapper trainingBookingMapper;

    @Override
    public void add(CreateBookingDto createBookingDto) {
        log.info("Добавление записи тренировки: {}", createBookingDto);
        TrainingBooking trainingBooking = trainingBookingMapper.toEntity(createBookingDto);
        trainingBookingRepository.save(trainingBooking);
    }

    @Override
    public BookingResponseDto findBookingById(long id) {
        log.info("Поиск записи тренировки по id: {}", id);
        TrainingBooking trainingBooking = trainingBookingRepository.findById(id).orElseThrow(() -> new NotFoundByIdException("Записи на тренировку по данному id не существует"));
        return trainingBookingMapper.toDto(trainingBooking);
    }

    @Override
    public List<BookingResponseDto> findByClientId(Long clientId) {
        log.info("Поиск всех записей тренировок клиента по его id: {}", clientId);
        List<TrainingBooking> trainingBookings = trainingBookingRepository.findByClientId(clientId);
        return trainingBookingMapper.toDtoList(trainingBookings);
    }

    @Override
    public List<BookingResponseDto> findByTrainingSessionId(Long trainingSessionId) {
        log.info("Поиск всех записей тренировок по id: {}", trainingSessionId);
        List<TrainingBooking> trainingBookings = trainingBookingRepository.findByTrainingSessionId(trainingSessionId);
        return trainingBookingMapper.toDtoList(trainingBookings);
    }

    @Override
    public BookingResponseDto findByClientIdAndTrainingSessionId(Long clientId, Long trainingSessionId) {
        log.info("Поиск записи тренировки клиента по id: {} {}", clientId, trainingSessionId);
        TrainingBooking trainingBooking = trainingBookingRepository.findByClientIdAndTrainingSessionId(clientId, trainingSessionId).orElseThrow(() -> new NotFoundByIdException("Записи на тренировку по данному id не существует"));
        return trainingBookingMapper.toDto(trainingBooking);
    }

    @Override
    public List<BookingResponseDto> findByStatus(StatusOfTrainingBooking status) {
        log.info("Поиск всех записей тренировок клиента по указанному статусу: {}", status);
        List<TrainingBooking> trainingBookings = trainingBookingRepository.findByStatus(status);
        return trainingBookingMapper.toDtoList(trainingBookings);
    }

    @Override
    public List<BookingResponseDto> showAll() {
        log.info("Список всех записей тренировок");
        List<TrainingBooking> trainingBookings = trainingBookingRepository.findAll();
        return trainingBookingMapper.toDtoList(trainingBookings);
    }

    @Override
    public void cancel(Long bookingId) {
        log.info("Отменить запись тренировки по id: {}", bookingId);
        TrainingBooking trainingBooking = trainingBookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundByIdException("Записи на тренировку по данному id не существует"));
        trainingBooking.setStatus(StatusOfTrainingBooking.CANCELLED);
        trainingBookingRepository.save(trainingBooking);
    }

    @Override
    public void markAttended(Long bookingId) {
        log.info("Клиент посетил тренировку, id записи: {}", bookingId);
        TrainingBooking trainingBooking = trainingBookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundByIdException("Записи на тренировку по данному id не существует"));
        trainingBooking.setAttended(true);
        trainingBooking.setStatus(StatusOfTrainingBooking.ATTENDED);
        trainingBookingRepository.save(trainingBooking);
    }

    @Override
    public void markNoShow(Long bookingId) {
        log.info("Клиент не явился на тренировку, id записи: {}", bookingId);
        TrainingBooking trainingBooking = trainingBookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundByIdException("Записи на тренировку по данному id не существует"));
        trainingBooking.setAttended(false);
        trainingBooking.setStatus(StatusOfTrainingBooking.NO_SHOW);
        trainingBookingRepository.save(trainingBooking);
    }

    @Override
    public void delete(Long bookingId) {
        log.info("Удаление записи тренировки по id: {}", bookingId);
        TrainingBooking trainingBooking = trainingBookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundByIdException("Записи на тренировку по данному id не существует"));
        trainingBookingRepository.delete(trainingBooking);
    }
}
