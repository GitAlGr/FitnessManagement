package org.alex.example.fitnessmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alex.example.fitnessmanagement.dto.ClientResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateClientDto;
import org.alex.example.fitnessmanagement.dto.UpdateClientDto;
import org.alex.example.fitnessmanagement.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addClientTest() throws Exception {
        CreateClientDto createClientDto = new CreateClientDto();
        createClientDto.setFirstName("Alex");
        createClientDto.setLastName("Kervy");
        createClientDto.setEmail("al@mail.com");
        createClientDto.setPhoneNumber("+79995552255");
        createClientDto.setDateOfBirth(LocalDate.of(2000, 5, 5));

        mockMvc.perform(post("/clients/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createClientDto)))
                .andExpect(status().isOk());

        verify(clientService, times(1)).add(any(CreateClientDto.class));
    }

    @Test
    public void findClientByIdTest() throws Exception {
        long clientId = 1L;
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(clientId);

        when(clientService.findClientById(clientId)).thenReturn(clientResponseDto);

        mockMvc.perform(get("/clients/by-id/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId));

        verify(clientService, times(1)).findClientById(clientId);
    }

    @Test
    public void findClientByFirstNameTest() throws Exception {
        String clientFirstName = "Alex";
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setFirstName(clientFirstName);

        when(clientService.findByFirstName(clientFirstName)).thenReturn(clientResponseDto);

        mockMvc.perform(get("/clients/by-firstName/{firstName}", clientFirstName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value(clientFirstName));

        verify(clientService, times(1)).findByFirstName(clientFirstName);
    }

    @Test
    public void findClientByLastNameTest() throws Exception {
        String clientLastName = "Kervy";
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setLastName(clientLastName);

        when(clientService.findByLastName(clientLastName)).thenReturn(clientResponseDto);

        mockMvc.perform(get("/clients/by-lastName/{lastName}", clientLastName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.lastName").value(clientLastName));

        verify(clientService, times(1)).findByLastName(clientLastName);
    }

    @Test
    public void findClientByEmailTest() throws Exception {
        String clientEmail = "al@mail.com";
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setEmail(clientEmail);

        when(clientService.findByEmail(clientEmail)).thenReturn(clientResponseDto);

        mockMvc.perform(get("/clients/by-email/{email}", clientEmail))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value(clientEmail));

        verify(clientService, times(1)).findByEmail(clientEmail);
    }

    @Test
    public void findClientByPhoneTest() throws Exception {
        String phone = "+79995552255";
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setPhoneNumber(phone);

        when(clientService.findByPhone(phone)).thenReturn(clientResponseDto);

        mockMvc.perform(get("/clients/by-phoneNumber/{phone}", phone))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.phoneNumber").value(phone));

        verify(clientService, times(1)).findByPhone(phone);
    }

    @Test
    public void findActiveClientsTest() throws Exception {
        boolean isActive = true;
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setActive(isActive);

        ClientResponseDto clientResponseDto2 = new ClientResponseDto();
        clientResponseDto2.setId(2L);
        clientResponseDto2.setActive(isActive);

        List<ClientResponseDto> clientResponseDtoList = new ArrayList<>();
        clientResponseDtoList.add(clientResponseDto);
        clientResponseDtoList.add(clientResponseDto2);

        when(clientService.findByActiveTrue()).thenReturn(clientResponseDtoList);

        mockMvc.perform(get("/clients/activeClients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(clientService, times(1)).findByActiveTrue();
    }

    @Test
    public void findUnactiveClientsTest() throws Exception {
        boolean isActive = false;
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setActive(isActive);

        ClientResponseDto clientResponseDto2 = new ClientResponseDto();
        clientResponseDto2.setId(2L);
        clientResponseDto2.setActive(isActive);

        List<ClientResponseDto> clientResponseDtoList = new ArrayList<>();
        clientResponseDtoList.add(clientResponseDto);
        clientResponseDtoList.add(clientResponseDto2);

        when(clientService.findByActiveFalse()).thenReturn(clientResponseDtoList);

        mockMvc.perform(get("/clients/unactiveClients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(clientService, times(1)).findByActiveFalse();
    }

    @Test
    public void findAllClientsTest() throws Exception {
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);

        ClientResponseDto clientResponseDto2 = new ClientResponseDto();
        clientResponseDto2.setId(2L);

        List<ClientResponseDto> clientResponseDtoList = new ArrayList<>();
        clientResponseDtoList.add(clientResponseDto);
        clientResponseDtoList.add(clientResponseDto2);

        when(clientService.showAllClients()).thenReturn(clientResponseDtoList);

        mockMvc.perform(get("/clients/allClients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(clientService, times(1)).showAllClients();
    }

    @Test
    public void updateClientTest() throws Exception {
        long clientId = 1L;

        UpdateClientDto updateClientDto = new UpdateClientDto();
        updateClientDto.setFirstName("Иван");
        updateClientDto.setLastName("Иванов");
        updateClientDto.setPhoneNumber("+79998885544");
        updateClientDto.setActive(false);

        mockMvc.perform(put("/clients/put/{clientId}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateClientDto)))
                .andExpect(status().isOk());
        verify(clientService, times(1)).updateInfoAboutClient(eq(clientId), any(UpdateClientDto.class));
    }

    @Test
    public void deleteClientTest() throws Exception {
        long clientId = 1L;

        mockMvc.perform(delete("/clients/delete/{clientId}", clientId))
                .andExpect(status().isOk());

        verify(clientService, times(1)).deleteClient(clientId);
    }
}
