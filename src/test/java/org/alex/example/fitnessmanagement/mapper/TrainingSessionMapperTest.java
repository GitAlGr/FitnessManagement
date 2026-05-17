package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.CreateTrainingSessionDto;
import org.alex.example.fitnessmanagement.dto.TrainingSessionResponseDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTraining;
import org.alex.example.fitnessmanagement.entity.Trainer;
import org.alex.example.fitnessmanagement.entity.TrainingSession;
import org.alex.example.fitnessmanagement.entity.TypeOfTrainingSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TrainingSessionMapperTest {

    private final TrainingSessionMapper trainingSessionMapper = new TrainingSessionMapperImpl();

    @Test
    public void toEntityTest() {
        CreateTrainingSessionDto createTrainingSessionDto = new CreateTrainingSessionDto();
        createTrainingSessionDto.setTrainerId(1L);
        createTrainingSessionDto.setName("Бокс");
        createTrainingSessionDto.setType(TypeOfTrainingSession.GROUP);
        createTrainingSessionDto.setDate(LocalDate.of(2026, 5, 10));
        createTrainingSessionDto.setStartTime(LocalTime.of(8, 0));
        createTrainingSessionDto.setDurationMinutes(60);
        createTrainingSessionDto.setMaxParticipants(20);


        TrainingSession trainingSession = trainingSessionMapper.toEntity(createTrainingSessionDto);

        assertNotNull(trainingSession);
        assertEquals(0, trainingSession.getId());
        assertEquals("Бокс", trainingSession.getName());
        assertEquals(TypeOfTrainingSession.GROUP, trainingSession.getType());
        assertEquals(LocalDate.of(2026, 5, 10), trainingSession.getDateOfTrainingSession());
        assertEquals(LocalTime.of(8, 0), trainingSession.getTimeOfBeginTraining());
        assertEquals(60, trainingSession.getDurationOfTrainingInMinutes());
        assertEquals(20, trainingSession.getMaxQuantityOfParticipants());
        assertEquals(StatusOfTraining.SCHEDULED, trainingSession.getStatus());
    }

    @Test
    public void toDtoTest() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setFirstName("Даниил");
        trainer.setLastName("Смирнов");

        TrainingSession session = new TrainingSession();
        session.setId(1);
        session.setTrainer(trainer);
        session.setName("Йога");
        session.setType(TypeOfTrainingSession.GROUP);
        session.setDateOfTrainingSession(LocalDate.of(2026, 5, 10));
        session.setTimeOfBeginTraining(LocalTime.of(8, 0));
        session.setDurationOfTrainingInMinutes(60);
        session.setMaxQuantityOfParticipants(20);
        session.setStatus(StatusOfTraining.SCHEDULED);

        TrainingSessionResponseDto dto = trainingSessionMapper.toDto(session);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals(1L, dto.getTrainerId());
        assertEquals("Даниил Смирнов", dto.getTrainerFullName());
        assertEquals("Йога", dto.getName());
        assertEquals(TypeOfTrainingSession.GROUP, dto.getType());
        assertEquals(LocalDate.of(2026, 5, 10), dto.getDate());
        assertEquals(LocalTime.of(8, 0), dto.getStartTime());
        assertEquals(60, dto.getDurationMinutes());
        assertEquals(20, dto.getMaxParticipants());
        assertEquals(StatusOfTraining.SCHEDULED, dto.getStatus());
    }


}
