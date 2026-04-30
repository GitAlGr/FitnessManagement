package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.CreateTrainingSessionDto;
import org.alex.example.fitnessmanagement.dto.TrainingSessionResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainingSessionDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTraining;

import java.time.LocalDate;
import java.util.List;

public interface TrainingSessionService {

    void add(CreateTrainingSessionDto createTrainingSessionDto);

    TrainingSessionResponseDto findById(long id);

    List<TrainingSessionResponseDto> findByTrainerId(Long id);

    List<TrainingSessionResponseDto> findByDate(LocalDate date);

    List<TrainingSessionResponseDto> findByDateBetween(LocalDate start, LocalDate end);

    List<TrainingSessionResponseDto> findByStatus(StatusOfTraining status);

    List<TrainingSessionResponseDto> findByTrainerIdAndDate(Long trainerId, LocalDate date);

    List<TrainingSessionResponseDto> showAll();

    void updateInfoAboutTrainingSession(Long trainingSessionId, UpdateTrainingSessionDto updateTrainingSessionDto);

    void deleteTrainingSession(Long trainingSessionId);
}
