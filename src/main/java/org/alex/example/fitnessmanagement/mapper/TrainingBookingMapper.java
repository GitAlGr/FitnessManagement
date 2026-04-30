package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.BookingResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateBookingDto;
import org.alex.example.fitnessmanagement.entity.TrainingBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface TrainingBookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "trainingSessionId", target = "trainingSession.id")
    @Mapping(target = "bookingDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "attended", constant = "false")
    @Mapping(target = "status", constant = "BOOKED")
    TrainingBooking toEntity(CreateBookingDto createBookingDto);

    List<TrainingBooking> toEntityList(List<CreateBookingDto> createBookingDtoList);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(target = "clientFullName", expression = "java(trainingBooking.getClient().getFirstName() + \" \" + trainingBooking.getClient().getLastName())")
    @Mapping(source = "trainingSession.id", target = "trainingSessionId")
    @Mapping(source = "trainingSession.name", target = "trainingSessionName")
    @Mapping(source = "trainingSession.dateOfTrainingSession", target = "trainingDate")
    @Mapping(source = "trainingSession.timeOfBeginTraining", target = "trainingStartTime")
    @Mapping(target = "trainerFullName", expression = "java(trainingBooking.getTrainingSession().getTrainer().getFirstName() + \" \" + trainingBooking.getTrainingSession().getTrainer().getLastName())")
    @Mapping(source = "bookingDate", target = "bookingDateTime")
    @Mapping(source = "attended", target = "attended")
    BookingResponseDto toDto(TrainingBooking trainingBooking);

    List<BookingResponseDto> toDtoList(List<TrainingBooking> trainingBookingList);

}


