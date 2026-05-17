package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.ClientResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateClientDto;
import org.alex.example.fitnessmanagement.entity.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClientMapperTest {

    private final ClientMapper clientMapper = new ClientMapperImpl();

    @Test
    public void toEntityTest(){
        CreateClientDto createClientDto = new CreateClientDto();
        createClientDto.setFirstName("Alex");
        createClientDto.setLastName("Kervy");
        createClientDto.setEmail("a.@mail.com");
        createClientDto.setPhoneNumber("+79995555555");
        createClientDto.setDateOfBirth(LocalDate.of(1955,5,18));

        Client client = clientMapper.toEntity(createClientDto);

        assertNotNull(client);
        assertEquals(0, client.getId());
        assertEquals("Alex", client.getFirstName());
        assertEquals("Kervy", client.getLastName());
        assertEquals("a.@mail.com", client.getEmail());
        assertEquals("+79995555555", client.getPhone());
        assertNotNull(client.getDateOfBirth());
    }

    @Test
    public void toDtoTest(){
        Client client = new Client();
        client.setId(1);
        client.setFirstName("Alex");
        client.setLastName("Kervy");
        client.setEmail("a.@mail.com");
        client.setPhone("+79995555555");
        client.setDateOfBirth(LocalDate.of(1955,5,18));

        ClientResponseDto clientResponseDto = clientMapper.toDto(client);

        assertNotNull(clientResponseDto);
        assertEquals(1, clientResponseDto.getId());
        assertEquals("Alex", clientResponseDto.getFirstName());
        assertEquals("Kervy", clientResponseDto.getLastName());
        assertEquals("a.@mail.com", clientResponseDto.getEmail());
        assertEquals("+79995555555", clientResponseDto.getPhoneNumber());
        assertNotNull(clientResponseDto.getDateOfBirth());
    }


}
