package org.alex.example.fitnessmanagement.repository;

import org.alex.example.fitnessmanagement.entity.StatusOfTrainingBooking;
import org.alex.example.fitnessmanagement.entity.TrainingBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingBookingRepository extends JpaRepository<TrainingBooking, Long> {

    List<TrainingBooking> findByClientId(Long clientId);

    List<TrainingBooking> findByTrainingSessionId(Long trainingSessionId);

    Optional<TrainingBooking> findByClientIdAndTrainingSessionId(Long clientId, Long trainingSessionId);

    List<TrainingBooking> findByStatus(StatusOfTrainingBooking status);
}
