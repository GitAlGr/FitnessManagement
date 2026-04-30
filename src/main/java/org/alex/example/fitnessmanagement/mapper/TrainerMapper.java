package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.CreateTrainerDto;
import org.alex.example.fitnessmanagement.dto.TrainerResponseDto;
import org.alex.example.fitnessmanagement.entity.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface TrainerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(target = "hireDate", expression = "java(LocalDate.now())")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "trainingSessions", ignore = true)
    Trainer toEntity(CreateTrainerDto createTrainerDto);

    List<Trainer> toEntityList(List<CreateTrainerDto> createTrainerDtoList);

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(target = "upcomingSessionsCount", ignore = true)
    TrainerResponseDto toDto(Trainer trainer);

    List<TrainerResponseDto> toDtoList(List<Trainer> trainerList);

}
