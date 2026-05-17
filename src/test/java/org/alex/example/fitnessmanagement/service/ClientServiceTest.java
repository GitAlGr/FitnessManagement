package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.ClientResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateClientDto;
import org.alex.example.fitnessmanagement.dto.UpdateClientDto;
import org.alex.example.fitnessmanagement.entity.Client;
import org.alex.example.fitnessmanagement.mapper.ClientMapper;
import org.alex.example.fitnessmanagement.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void addClientTest() {
        CreateClientDto createClientDto = new CreateClientDto();
        createClientDto.setFirstName("Alex");
        createClientDto.setLastName("Kervy");
        createClientDto.setEmail("al@mail.com");
        createClientDto.setPhoneNumber("79995554466");
        createClientDto.setDateOfBirth(LocalDate.of(1985, 2, 15));

        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Alex");
        client.setLastName("Kervy");
        client.setEmail("al@mail.com");
        client.setPhone("79995554466");
        client.setDateOfBirth(LocalDate.of(1985, 2, 15));

        when(clientMapper.toEntity(createClientDto)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);

        clientService.add(createClientDto);

        verify(clientMapper, times(1)).toEntity(createClientDto);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void findClientByIdTest() {
        long clientId = 1L;

        Client client = new Client();
        client.setId(clientId);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(clientId);

        when(clientMapper.toDto(client)).thenReturn(clientResponseDto);
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        clientService.findClientById(clientId);

        verify(clientMapper, times(1)).toDto(client);
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void findByFirstNameTest() {
        String name = "Alex";

        Client client = new Client();
        client.setId(1L);
        client.setFirstName(name);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setFirstName(name);

        when(clientMapper.toDto(client)).thenReturn(clientResponseDto);
        when(clientRepository.findByFirstName(name)).thenReturn(Optional.of(client));

        clientService.findByFirstName(name);

        verify(clientMapper, times(1)).toDto(client);
        verify(clientRepository, times(1)).findByFirstName(name);
    }

    @Test
    public void findByLastNameTest() {
        String lastName = "Kervy";

        Client client = new Client();
        client.setId(1L);
        client.setLastName(lastName);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setLastName(lastName);

        when(clientMapper.toDto(client)).thenReturn(clientResponseDto);
        when(clientRepository.findByLastName(lastName)).thenReturn(Optional.of(client));

        clientService.findByLastName(lastName);

        verify(clientMapper, times(1)).toDto(client);
        verify(clientRepository, times(1)).findByLastName(lastName);
    }

    @Test
    public void findByEmailTest() {
        String email = "al@mail.com";

        Client client = new Client();
        client.setId(1L);
        client.setEmail(email);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setEmail(email);

        when(clientMapper.toDto(client)).thenReturn(clientResponseDto);
        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(client));

        clientService.findByEmail(email);

        verify(clientMapper, times(1)).toDto(client);
        verify(clientRepository, times(1)).findByEmail(email);
    }

    @Test
    public void findByPhoneNumberTest() {
        String phoneNumber = "79995554466";

        Client client = new Client();
        client.setId(1L);
        client.setPhone(phoneNumber);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setPhoneNumber(phoneNumber);

        when(clientMapper.toDto(client)).thenReturn(clientResponseDto);
        when(clientRepository.findByPhone(phoneNumber)).thenReturn(Optional.of(client));

        clientService.findByPhone(phoneNumber);

        verify(clientMapper, times(1)).toDto(client);
        verify(clientRepository, times(1)).findByPhone(phoneNumber);
    }

    @Test
    public void findByActiveTrueTest() {
        boolean isActive = true;

        Client client = new Client();
        client.setId(1L);
        client.setActive(isActive);

        Client client2 = new Client();
        client2.setId(2L);
        client2.setActive(isActive);

        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        clientList.add(client2);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setActive(isActive);

        ClientResponseDto clientResponseDto2 = new ClientResponseDto();
        clientResponseDto2.setId(2L);
        clientResponseDto2.setActive(isActive);

        List<ClientResponseDto> clientResponseDtoList = new ArrayList<>();
        clientResponseDtoList.add(clientResponseDto);
        clientResponseDtoList.add(clientResponseDto2);

        when(clientMapper.toDtoList(clientList)).thenReturn(clientResponseDtoList);
        when(clientRepository.findByActiveTrue()).thenReturn(clientList);

        clientService.findByActiveTrue();

        verify(clientMapper, times(1)).toDtoList(clientList);
        verify(clientRepository, times(1)).findByActiveTrue();
    }

    @Test
    public void findByActiveFalseTest() {
        boolean isActive = false;

        Client client = new Client();
        client.setId(1L);
        client.setActive(isActive);

        Client client2 = new Client();
        client2.setId(2L);
        client2.setActive(isActive);

        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        clientList.add(client2);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setActive(isActive);

        ClientResponseDto clientResponseDto2 = new ClientResponseDto();
        clientResponseDto2.setId(2L);
        clientResponseDto2.setActive(isActive);

        List<ClientResponseDto> clientResponseDtoList = new ArrayList<>();
        clientResponseDtoList.add(clientResponseDto);
        clientResponseDtoList.add(clientResponseDto2);

        when(clientMapper.toDtoList(clientList)).thenReturn(clientResponseDtoList);
        when(clientRepository.findByActiveFalse()).thenReturn(clientList);

        clientService.findByActiveFalse();

        verify(clientMapper, times(1)).toDtoList(clientList);
        verify(clientRepository, times(1)).findByActiveFalse();
    }

    @Test
    public void findAllClientsTest() {
        Client client = new Client();
        client.setId(1L);

        Client client2 = new Client();
        client2.setId(2L);

        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        clientList.add(client2);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);

        ClientResponseDto clientResponseDto2 = new ClientResponseDto();
        clientResponseDto2.setId(2L);

        List<ClientResponseDto> clientResponseDtoList = new ArrayList<>();
        clientResponseDtoList.add(clientResponseDto);
        clientResponseDtoList.add(clientResponseDto2);

        when(clientMapper.toDtoList(clientList)).thenReturn(clientResponseDtoList);
        when(clientRepository.findAll()).thenReturn(clientList);

        clientService.showAllClients();

        verify(clientMapper, times(1)).toDtoList(clientList);
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void updateClientTest() {
        long clientId = 1L;

        Client client = new Client();
        client.setId(clientId);
        client.setFirstName("Alex");
        client.setLastName("Kervy");
        client.setEmail("al@mail.com");
        client.setPhone("79995554466");
        client.setDateOfBirth(LocalDate.of(1985, 2, 15));
        client.setActive(true);

        UpdateClientDto updateClientDto = new UpdateClientDto();
        updateClientDto.setFirstName("Дмитрий");
        updateClientDto.setLastName("Берестов");
        updateClientDto.setPhoneNumber("79995554477");
        updateClientDto.setActive(false);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        clientService.updateInfoAboutClient(clientId, updateClientDto);

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void deleteClientTest() {
        long clientId = 1L;

        Client client = new Client();
        client.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        clientService.deleteClient(clientId);

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).delete(client);
    }
}
