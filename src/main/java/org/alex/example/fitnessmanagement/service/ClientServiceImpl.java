package org.alex.example.fitnessmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alex.example.fitnessmanagement.dto.ClientResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateClientDto;
import org.alex.example.fitnessmanagement.dto.UpdateClientDto;
import org.alex.example.fitnessmanagement.entity.Client;
import org.alex.example.fitnessmanagement.error.*;
import org.alex.example.fitnessmanagement.mapper.ClientMapper;
import org.alex.example.fitnessmanagement.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public void add(CreateClientDto createClientDto) {
        log.info("Добавление клиента в базу: {}", createClientDto);
        if (clientRepository.existsByEmail(createClientDto.getEmail())) {
            throw new BusinessFitnessException("Клиент с таким email уже существует");
        }
        if (createClientDto.getPhoneNumber() != null && clientRepository.existsByPhone(createClientDto.getPhoneNumber())) {
            throw new BusinessFitnessException("Клиент с таким номером телефона уже существует");
        }
        Client client = clientMapper.toEntity(createClientDto);
        clientRepository.save(client);
    }

    @Override
    public ClientResponseDto findClientById(long id) {
        log.info("Поиск клиента по id: {}", id);
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundByIdException("Клиента с данным id не существует"));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientResponseDto findByFirstName(String firstName) {
        log.info("Поиск клиента по имени: {}", firstName);
        Client client = clientRepository.findByFirstName(firstName).orElseThrow(() -> new NotFoundByNameException("Клиента с данным именем не существует"));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientResponseDto findByLastName(String lastName) {
        log.info("Поиск клиента по фамилии: {}", lastName);
        Client client = clientRepository.findByLastName(lastName).orElseThrow(() -> new NotFoundByNameException("Клиента с данной фамилией не существует"));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientResponseDto findByEmail(String email) {
        log.info("Поиск клиента по адресу электронной почты: {}", email);
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new NotFoundByEmailException("Клиента с данным email не существует"));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientResponseDto findByPhone(String phone) {
        log.info("Поиск клиента по номеру телефона: {}", phone);
        Client client = clientRepository.findByPhone(phone).orElseThrow(() -> new NotFoundByPhoneNumberException("Клиента с данным номером телефона не существует"));
        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientResponseDto> findByActiveTrue() {
        log.info("Поиск активных клиентов");
        List<Client> clients = clientRepository.findByIsActiveTrue();
        return clientMapper.toDtoList(clients);
    }

    @Override
    public List<ClientResponseDto> findByActiveFalse() {
        log.info("Поиск неактивных клиентов");
        List<Client> clients = clientRepository.findByIsActiveFalse();
        return clientMapper.toDtoList(clients);
    }

    @Override
    public List<ClientResponseDto> showAllClients() {
        log.info("Вывести список всех клиентов");
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toDtoList(clients);
    }

    @Override
    public void updateInfoAboutClient(long clientId, UpdateClientDto updateClientDto) {
        log.info("Обновление данных клиента по его id: {} {} ", clientId, updateClientDto);
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundByIdException("Клиента с данным id не существует"));
        if (updateClientDto.getFirstName() != null) {
            client.setFirstName(updateClientDto.getFirstName());
        }
        if (updateClientDto.getLastName() != null) {
            client.setLastName(updateClientDto.getLastName());
        }
        if (updateClientDto.getPhoneNumber() != null) {
            client.setPhone(updateClientDto.getPhoneNumber());
        }
        if (updateClientDto.getActive() != null) {
            client.setActive(updateClientDto.getActive());
        }
        clientRepository.save(client);
    }

    @Override
    public void deleteClient(long clientId) {
        log.info("Удаление клиента по его id: {}", clientId);
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundByIdException("Клиента с данным id не существует"));
        clientRepository.delete(client);
    }
}
