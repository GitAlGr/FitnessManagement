package org.alex.example.fitnessmanagement.repository;

import org.alex.example.fitnessmanagement.entity.StatusOfTraining;
import org.alex.example.fitnessmanagement.entity.TrainingSession;
import org.alex.example.fitnessmanagement.entity.TypeOfTrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {

    List<TrainingSession> findByDateOfTrainingSession(LocalDate localDate);

    List<TrainingSession> findByDateOfTrainingSessionBetween(LocalDate start, LocalDate end);

    List<TrainingSession> findByTrainerId(Long trainerId);

    List<TrainingSession> findByTrainerIdAndDateOfTrainingSession(Long trainerId, LocalDate date);

    List<TrainingSession> findByStatus(StatusOfTraining status);

    List<TrainingSession> findByType(TypeOfTrainingSession type);
}
