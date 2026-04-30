package org.alex.example.fitnessmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alex.example.fitnessmanagement.dto.CreateTrainingSessionDto;
import org.alex.example.fitnessmanagement.dto.TrainingSessionResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainingSessionDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTraining;
import org.alex.example.fitnessmanagement.entity.TrainingSession;
import org.alex.example.fitnessmanagement.error.NotFoundByIdException;
import org.alex.example.fitnessmanagement.mapper.TrainingSessionMapper;
import org.alex.example.fitnessmanagement.repository.TrainingSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingSessionServiceImpl implements TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;
    private final TrainingSessionMapper trainingSessionMapper;

    @Override
    public void add(CreateTrainingSessionDto createTrainingSessionDto) {
        log.info("Добавление тренировки: {}", createTrainingSessionDto);
        TrainingSession trainingSession = trainingSessionMapper.toEntity(createTrainingSessionDto);
        trainingSessionRepository.save(trainingSession);
    }

    @Override
    public TrainingSessionResponseDto findById(long id) {
        log.info("Поиск тренировки по id: {}", id);
        TrainingSession trainingSession = trainingSessionRepository.findById(id).orElseThrow(() -> new NotFoundByIdException("Тренировки с данным id не существует"));
        return trainingSessionMapper.toDto(trainingSession);
    }

    @Override
    public List<TrainingSessionResponseDto> findByTrainerId(Long id) {
        log.info("Список тренировок указанного тренера по его id: {}", id);
        List<TrainingSession> trainingSessions = trainingSessionRepository.findByTrainerId(id);
        return trainingSessionMapper.toDtoList(trainingSessions);
    }

    @Override
    public List<TrainingSessionResponseDto> findByDate(LocalDate date) {
        log.info("Список тренировок на указанную дату: {}", date);
        List<TrainingSession> trainingSessions = trainingSessionRepository.findByDateOfTrainingSession(date);
        return trainingSessionMapper.toDtoList(trainingSessions);
    }

    @Override
    public List<TrainingSessionResponseDto> findByDateBetween(LocalDate start, LocalDate end) {
        log.info("Список тренировок в промежуток между конкретными датами: {} {}", start, end);
        List<TrainingSession> trainingSessions = trainingSessionRepository.findByDateOfTrainingSessionBetween(start, end);
        return trainingSessionMapper.toDtoList(trainingSessions);
    }

    @Override
    public List<TrainingSessionResponseDto> findByStatus(StatusOfTraining status) {
        log.info("Список тренировок с указанным статусом: {}", status);
        List<TrainingSession> trainingSessions = trainingSessionRepository.findByStatus(status);
        return trainingSessionMapper.toDtoList(trainingSessions);
    }

    @Override
    public List<TrainingSessionResponseDto> findByTrainerIdAndDate(Long trainerId, LocalDate date) {
        log.info("Список тренировок с указанным тренером на конкретную дату: {} {}", trainerId, date);
        List<TrainingSession> trainingSessions = trainingSessionRepository.findByTrainerIdAndDateOfTrainingSession(trainerId, date);
        return trainingSessionMapper.toDtoList(trainingSessions);
    }

    @Override
    public List<TrainingSessionResponseDto> showAll() {
        log.info("Список всех тренировок");
        List<TrainingSession> trainingSessions = trainingSessionRepository.findAll();
        return trainingSessionMapper.toDtoList(trainingSessions);
    }

    @Override
    public void updateInfoAboutTrainingSession(Long trainingSessionId, UpdateTrainingSessionDto updateTrainingSessionDto) {
        log.info("Изменение информации тренирвки с указанным id: {} {}", trainingSessionId, updateTrainingSessionDto);
        TrainingSession trainingSession = trainingSessionRepository.findById(trainingSessionId).orElseThrow(() -> new NotFoundByIdException("Тренировки с данным id не существует"));
        if (updateTrainingSessionDto.getName() != null) {
            trainingSession.setName(updateTrainingSessionDto.getName());
        }
        if (updateTrainingSessionDto.getDate() != null) {
            trainingSession.setDateOfTrainingSession(updateTrainingSessionDto.getDate());
        }
        if (updateTrainingSessionDto.getStatus() != null) {
            trainingSession.setStatus(updateTrainingSessionDto.getStatus());
        }
        if (updateTrainingSessionDto.getStartTime() != null) {
            trainingSession.setTimeOfBeginTraining(updateTrainingSessionDto.getStartTime());
        }
        if (updateTrainingSessionDto.getDurationMinutes() != null) {
            trainingSession.setDurationOfTrainingInMinutes(updateTrainingSessionDto.getDurationMinutes());
        }
        if (updateTrainingSessionDto.getMaxParticipants() != null) {
            trainingSession.setMaxQuantityOfParticipants(updateTrainingSessionDto.getMaxParticipants());
        }
        trainingSessionRepository.save(trainingSession);
    }

    @Override
    public void deleteTrainingSession(Long trainingSessionId) {
        log.info("Удаление тренировки по id: {}", trainingSessionId);
        TrainingSession trainingSession = trainingSessionRepository.findById(trainingSessionId).orElseThrow(() -> new NotFoundByIdException("Тренировки с данным id не существует"));
        trainingSessionRepository.delete(trainingSession);
    }
}
