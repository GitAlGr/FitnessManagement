package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.ClientResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateClientDto;
import org.alex.example.fitnessmanagement.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring",imports = {LocalDate.class})
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(target = "dateOfRegistration", expression = "java(LocalDate.now())")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "memberships", ignore = true)
    @Mapping(target = "trainingBookings", ignore = true)
    Client toEntity(CreateClientDto createClientDto);

    List<Client> toEntityList(List<CreateClientDto> createClientDtoList);

    @Mapping(source = "phone",target = "phoneNumber")
    @Mapping(source = "dateOfRegistration",target = "registrationDate")
    ClientResponseDto toDto(Client client);

    List<ClientResponseDto> toDtoList(List<Client> clientList);
}
