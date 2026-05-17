package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.CreateTrainingSessionDto;
import org.alex.example.fitnessmanagement.dto.TrainingSessionResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainingSessionDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTraining;
import org.alex.example.fitnessmanagement.entity.TrainingSession;
import org.alex.example.fitnessmanagement.mapper.TrainingSessionMapper;
import org.alex.example.fitnessmanagement.repository.TrainingSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingSessionServiceTest {

    @Mock
    private TrainingSessionRepository trainingSessionRepository;

    @Mock
    private TrainingSessionMapper trainingSessionMapper;

    @InjectMocks
    private TrainingSessionServiceImpl trainingSessionService;

    @Test
    public void addTrainingSessionTest() {
        CreateTrainingSessionDto createTrainingSessionDto = new CreateTrainingSessionDto();
        createTrainingSessionDto.setName("Бокс");

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(1L);
        trainingSession.setName("Бокс");

        when(trainingSessionMapper.toEntity(createTrainingSessionDto)).thenReturn(trainingSession);
        when(trainingSessionRepository.save(trainingSession)).thenReturn(trainingSession);

        trainingSessionService.add(createTrainingSessionDto);

        verify(trainingSessionMapper, times(1)).toEntity(createTrainingSessionDto);
        verify(trainingSessionRepository, times(1)).save(trainingSession);
    }

    @Test
    public void findTrainingSessionByIdTest() {
        long trainingSessionId = 1L;

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(trainingSessionId);

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(trainingSessionId);

        when(trainingSessionMapper.toDto(trainingSession)).thenReturn(trainingSessionResponseDto);
        when(trainingSessionRepository.findById(trainingSessionId)).thenReturn(Optional.of(trainingSession));

        trainingSessionService.findById(trainingSessionId);

        verify(trainingSessionMapper, times(1)).toDto(trainingSession);
        verify(trainingSessionRepository, times(1)).findById(trainingSessionId);
    }

    @Test
    public void findTrainingSessionByTrainerIdTest() {
        long trainerId = 1L;

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(1L);

        TrainingSession trainingSession2 = new TrainingSession();
        trainingSession2.setId(2L);

        List<TrainingSession> trainingSessionList = new ArrayList<>();
        trainingSessionList.add(trainingSession);
        trainingSessionList.add(trainingSession2);

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);
        trainingSessionResponseDto.setTrainerId(trainerId);

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);
        trainingSessionResponseDto2.setTrainerId(trainerId);

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionMapper.toDtoList(trainingSessionList)).thenReturn(trainingSessionResponseDtoList);
        when(trainingSessionRepository.findByTrainerId(trainerId)).thenReturn(trainingSessionList);

        trainingSessionService.findByTrainerId(trainerId);

        verify(trainingSessionMapper, times(1)).toDtoList(trainingSessionList);
        verify(trainingSessionRepository, times(1)).findByTrainerId(trainerId);
    }

    @Test
    public void findTrainingSessionByDateTest() {
        LocalDate date = LocalDate.of(2026, 5, 5);

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(1L);
        trainingSession.setDateOfTrainingSession(date);

        TrainingSession trainingSession2 = new TrainingSession();
        trainingSession2.setId(2L);
        trainingSession2.setDateOfTrainingSession(date);

        List<TrainingSession> trainingSessionList = new ArrayList<>();
        trainingSessionList.add(trainingSession);
        trainingSessionList.add(trainingSession2);

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);
        trainingSessionResponseDto.setDate(date);

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);
        trainingSessionResponseDto2.setDate(date);

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionMapper.toDtoList(trainingSessionList)).thenReturn(trainingSessionResponseDtoList);
        when(trainingSessionRepository.findByDateOfTrainingSession(date)).thenReturn(trainingSessionList);

        trainingSessionService.findByDate(date);

        verify(trainingSessionMapper, times(1)).toDtoList(trainingSessionList);
        verify(trainingSessionRepository, times(1)).findByDateOfTrainingSession(date);
    }

    @Test
    public void findTrainingSessionByDateBetweenTest() {
        LocalDate start = LocalDate.of(2026, 5, 5);
        LocalDate end = LocalDate.of(2026, 3, 5);

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(1L);
        trainingSession.setDateOfTrainingSession(LocalDate.of(2026, 4, 10));

        TrainingSession trainingSession2 = new TrainingSession();
        trainingSession2.setId(2L);
        trainingSession2.setDateOfTrainingSession(LocalDate.of(2026, 3, 11));

        List<TrainingSession> trainingSessionList = new ArrayList<>();
        trainingSessionList.add(trainingSession);
        trainingSessionList.add(trainingSession2);

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);
        trainingSessionResponseDto.setDate(LocalDate.of(2026, 4, 10));

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);
        trainingSessionResponseDto2.setDate(LocalDate.of(2026, 3, 11));

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionMapper.toDtoList(trainingSessionList)).thenReturn(trainingSessionResponseDtoList);
        when(trainingSessionRepository.findByDateOfTrainingSessionBetween(start, end)).thenReturn(trainingSessionList);

        trainingSessionService.findByDateBetween(start, end);

        verify(trainingSessionMapper, times(1)).toDtoList(trainingSessionList);
        verify(trainingSessionRepository, times(1)).findByDateOfTrainingSessionBetween(start, end);
    }

    @Test
    public void findTrainingSessionByStatusTest() {
        StatusOfTraining status = StatusOfTraining.COMPLETED;

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(1L);
        trainingSession.setStatus(status);

        TrainingSession trainingSession2 = new TrainingSession();
        trainingSession2.setId(2L);
        trainingSession2.setStatus(StatusOfTraining.CANCELLED);

        List<TrainingSession> trainingSessionList = new ArrayList<>();
        trainingSessionList.add(trainingSession);
        trainingSessionList.add(trainingSession2);

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);
        trainingSessionResponseDto.setStatus(status);

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);
        trainingSessionResponseDto2.setStatus(StatusOfTraining.CANCELLED);

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionMapper.toDtoList(trainingSessionList)).thenReturn(trainingSessionResponseDtoList);
        when(trainingSessionRepository.findByStatus(status)).thenReturn(trainingSessionList);

        trainingSessionService.findByStatus(status);

        verify(trainingSessionMapper, times(1)).toDtoList(trainingSessionList);
        verify(trainingSessionRepository, times(1)).findByStatus(status);
    }

    @Test
    public void findTrainingSessionByTrainerIdAndDateTest() {
        long trainerId = 1L;
        LocalDate date = LocalDate.of(2026, 3, 5);

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(1L);
        trainingSession.setDateOfTrainingSession(date);

        TrainingSession trainingSession2 = new TrainingSession();
        trainingSession2.setId(2L);
        trainingSession2.setDateOfTrainingSession(LocalDate.of(2026, 3, 11));

        List<TrainingSession> trainingSessionList = new ArrayList<>();
        trainingSessionList.add(trainingSession);
        trainingSessionList.add(trainingSession2);

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);
        trainingSessionResponseDto.setTrainerId(trainerId);
        trainingSessionResponseDto.setDate(date);

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);
        trainingSessionResponseDto2.setTrainerId(trainerId);
        trainingSessionResponseDto2.setDate(LocalDate.of(2026, 3, 11));

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionMapper.toDtoList(trainingSessionList)).thenReturn(trainingSessionResponseDtoList);
        when(trainingSessionRepository.findByTrainerIdAndDateOfTrainingSession(trainerId, date)).thenReturn(trainingSessionList);

        trainingSessionService.findByTrainerIdAndDate(trainerId, date);

        verify(trainingSessionMapper, times(1)).toDtoList(trainingSessionList);
        verify(trainingSessionRepository, times(1)).findByTrainerIdAndDateOfTrainingSession(trainerId, date);
    }

    @Test
    public void findAllTrainingSessionsTest() {

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(1L);

        TrainingSession trainingSession2 = new TrainingSession();
        trainingSession2.setId(2L);

        List<TrainingSession> trainingSessionList = new ArrayList<>();
        trainingSessionList.add(trainingSession);
        trainingSessionList.add(trainingSession2);

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionMapper.toDtoList(trainingSessionList)).thenReturn(trainingSessionResponseDtoList);
        when(trainingSessionRepository.findAll()).thenReturn(trainingSessionList);

        trainingSessionService.showAll();

        verify(trainingSessionMapper, times(1)).toDtoList(trainingSessionList);
        verify(trainingSessionRepository, times(1)).findAll();
    }

    @Test
    public void updateTrainingSessionTest() {
        long trainingSessionId = 1L;

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(trainingSessionId);
        trainingSession.setName("Бокс");
        trainingSession.setDateOfTrainingSession(LocalDate.of(2026, 5, 10));
        trainingSession.setStatus(StatusOfTraining.SCHEDULED);
        trainingSession.setTimeOfBeginTraining(LocalTime.of(11, 0, 0));
        trainingSession.setMaxQuantityOfParticipants(10);
        trainingSession.setDurationOfTrainingInMinutes(120);

        UpdateTrainingSessionDto updateTrainingSessionDto = new UpdateTrainingSessionDto();
        updateTrainingSessionDto.setName("Танцы");
        updateTrainingSessionDto.setDate(LocalDate.of(2026, 5, 11));
        updateTrainingSessionDto.setStatus(StatusOfTraining.CANCELLED);
        updateTrainingSessionDto.setStartTime(LocalTime.of(12, 0, 0));
        updateTrainingSessionDto.setMaxParticipants(12);
        updateTrainingSessionDto.setDurationMinutes(110);

        when(trainingSessionRepository.findById(trainingSessionId)).thenReturn(Optional.of(trainingSession));

        trainingSessionService.updateInfoAboutTrainingSession(trainingSessionId, updateTrainingSessionDto);

        verify(trainingSessionRepository, times(1)).findById(trainingSessionId);
        verify(trainingSessionRepository, times(1)).save(trainingSession);
    }

    @Test
    public void deleteTrainingSessionTest() {
        long trainingSessionId = 1L;

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId(trainingSessionId);

        when(trainingSessionRepository.findById(trainingSessionId)).thenReturn(Optional.of(trainingSession));

        trainingSessionService.deleteTrainingSession(trainingSessionId);

        verify(trainingSessionRepository, times(1)).findById(trainingSessionId);
        verify(trainingSessionRepository, times(1)).delete(trainingSession);
    }

}
