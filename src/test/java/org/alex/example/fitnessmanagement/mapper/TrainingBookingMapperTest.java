package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.BookingResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateBookingDto;
import org.alex.example.fitnessmanagement.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrainingBookingMapperTest {

    private final TrainingBookingMapper trainingBookingMapper = new TrainingBookingMapperImpl();

    @Test
    public void toEntityTest() {
        CreateBookingDto createBookingDto = new CreateBookingDto();
        createBookingDto.setClientId(1L);
        createBookingDto.setTrainingSessionId(1L);

        TrainingBooking trainingBooking = trainingBookingMapper.toEntity(createBookingDto);

        assertNotNull(trainingBooking);
        assertEquals(0, trainingBooking.getId());
        assertNotNull(trainingBooking.getClient());
        assertEquals(1L, trainingBooking.getClient().getId());
        assertNotNull(trainingBooking.getTrainingSession());
        assertEquals(1L, trainingBooking.getTrainingSession().getId());
        assertEquals(StatusOfTrainingBooking.BOOKED, trainingBooking.getStatus());
        assertFalse(trainingBooking.isAttended());
        assertNotNull(trainingBooking.getBookingDate());
    }

    @Test
    public void toDtoTest() {
        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Иван");
        client.setLastName("Петров");

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setFirstName("Анна");
        trainer.setLastName("Смирнова");

        TrainingSession session = new TrainingSession();
        session.setId(2L);
        session.setName("Утренняя йога");
        session.setDateOfTrainingSession(LocalDate.of(2026, 5, 10));
        session.setTimeOfBeginTraining(LocalTime.of(8, 0));
        session.setTrainer(trainer);

        TrainingBooking booking = new TrainingBooking();
        booking.setId(1);
        booking.setClient(client);
        booking.setTrainingSession(session);
        booking.setBookingDate(LocalDateTime.of(2026, 5, 1, 12, 0));
        booking.setAttended(false);
        booking.setStatus(StatusOfTrainingBooking.BOOKED);

        BookingResponseDto dto = trainingBookingMapper.toDto(booking);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals(1L, dto.getClientId());
        assertEquals("Иван Петров", dto.getClientFullName());
        assertEquals(2L, dto.getTrainingSessionId());
        assertEquals("Утренняя йога", dto.getTrainingSessionName());
        assertEquals(LocalDate.of(2026, 5, 10), dto.getTrainingDate());
        assertEquals(LocalTime.of(8, 0), dto.getTrainingStartTime());
        assertEquals("Анна Смирнова", dto.getTrainerFullName());
        assertEquals(StatusOfTrainingBooking.BOOKED, dto.getStatus());
        assertFalse(dto.getAttended());
    }
}
