package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.ClientResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateClientDto;
import org.alex.example.fitnessmanagement.dto.UpdateClientDto;

import java.util.List;

public interface ClientService {

    void add(CreateClientDto createClientDto);

    ClientResponseDto findClientById(long id);

    ClientResponseDto findByFirstName(String firstName);

    ClientResponseDto findByLastName(String lastName);

    ClientResponseDto findByEmail(String email);

    ClientResponseDto findByPhone(String phone);

    List<ClientResponseDto> findByActiveTrue();

    List<ClientResponseDto> findByActiveFalse();

    List<ClientResponseDto> showAllClients();

    void updateInfoAboutClient(long clientId, UpdateClientDto updateClientDto);

    void deleteClient(long clientId);
}
