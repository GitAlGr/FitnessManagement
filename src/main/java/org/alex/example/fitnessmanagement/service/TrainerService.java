package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.CreateTrainerDto;
import org.alex.example.fitnessmanagement.dto.TrainerResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainerDto;

import java.util.List;

public interface TrainerService {

    void add(CreateTrainerDto createTrainerDto);

    TrainerResponseDto findById(long id);

    TrainerResponseDto findByFirstName(String firstName);

    TrainerResponseDto findByLastName(String lastName);

    TrainerResponseDto findByEmail(String email);

    TrainerResponseDto findByPhone(String phone);

    List<TrainerResponseDto> findBySpecialization(String specialization);

    List<TrainerResponseDto> findByActiveTrue();

    List<TrainerResponseDto> findByActiveFalse();

    List<TrainerResponseDto> showAllTrainers();

    void updateInfoAboutTrainer(long trainerId, UpdateTrainerDto updateTrainerDto);

    void deleteTrainer(long trainerId);
}
