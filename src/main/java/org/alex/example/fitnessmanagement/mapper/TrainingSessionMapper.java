package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.CreateTrainingSessionDto;
import org.alex.example.fitnessmanagement.dto.TrainingSessionResponseDto;
import org.alex.example.fitnessmanagement.entity.TrainingSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface TrainingSessionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "trainerId", target = "trainer.id")
    @Mapping(source = "date", target = "dateOfTrainingSession")
    @Mapping(source = "startTime", target = "timeOfBeginTraining")
    @Mapping(source = "durationMinutes", target = "durationOfTrainingInMinutes")
    @Mapping(source = "maxParticipants", target = "maxQuantityOfParticipants")
    @Mapping(target = "status", constant = "SCHEDULED")
    TrainingSession toEntity(CreateTrainingSessionDto createTrainingSessionDto);

    List<TrainingSession> toEntityList(List<CreateTrainingSessionDto> createTrainingSessionDtoList);

    @Mapping(source = "trainer.id", target = "trainerId")
    @Mapping(target = "trainerFullName", expression = "java(trainingSession.getTrainer().getFirstName() + \" \" + trainingSession.getTrainer().getLastName())")
    @Mapping(source = "dateOfTrainingSession", target = "date")
    @Mapping(source = "timeOfBeginTraining", target = "startTime")
    @Mapping(source = "durationOfTrainingInMinutes", target = "durationMinutes")
    @Mapping(source = "maxQuantityOfParticipants", target = "maxParticipants")
    @Mapping(target = "currentParticipants", ignore = true)
    @Mapping(target = "available", ignore = true)
    TrainingSessionResponseDto toDto(TrainingSession trainingSession);

    List<TrainingSessionResponseDto> toDtoList(List<TrainingSession> trainingSessionList);

}
