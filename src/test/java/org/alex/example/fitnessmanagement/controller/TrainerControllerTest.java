package org.alex.example.fitnessmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alex.example.fitnessmanagement.dto.CreateTrainerDto;
import org.alex.example.fitnessmanagement.dto.TrainerResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainerDto;
import org.alex.example.fitnessmanagement.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainerController.class)
public class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainerService trainerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addTrainerTest() throws Exception {
        CreateTrainerDto createTrainerDto = new CreateTrainerDto();
        createTrainerDto.setFirstName("Alex");
        createTrainerDto.setLastName("Kervy");
        createTrainerDto.setEmail("al@mail.com");
        createTrainerDto.setPhoneNumber("+79995552255");
        createTrainerDto.setSpecialization("Бокс");

        mockMvc.perform(post("/trainers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTrainerDto)))
                .andExpect(status().isOk());

        verify(trainerService, times(1)).add(any(CreateTrainerDto.class));
    }

    @Test
    public void findTrainerByIdTest() throws Exception {
        long trainerId = 1L;

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(trainerId);

        when(trainerService.findById(trainerId)).thenReturn(trainerResponseDto);

        mockMvc.perform(get("/trainers/by-trainerId/{trainerId}", trainerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(trainerId));

        verify(trainerService, times(1)).findById(trainerId);
    }

    @Test
    public void findTrainerByFirstNameTest() throws Exception {
        String firstName = "Alex";

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setFirstName(firstName);

        when(trainerService.findByFirstName(firstName)).thenReturn(trainerResponseDto);

        mockMvc.perform(get("/trainers/by-firstName/{firstName}", firstName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value(firstName));

        verify(trainerService, times(1)).findByFirstName(firstName);
    }

    @Test
    public void findTrainerByLastNameTest() throws Exception {
        String lastName = "Kervy";

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setLastName(lastName);

        when(trainerService.findByLastName(lastName)).thenReturn(trainerResponseDto);

        mockMvc.perform(get("/trainers/by-lastName/{lastName}", lastName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.lastName").value(lastName));

        verify(trainerService, times(1)).findByLastName(lastName);
    }

    @Test
    public void findTrainerByEmailTest() throws Exception {
        String email = "al@mail.com";

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setEmail(email);

        when(trainerService.findByEmail(email)).thenReturn(trainerResponseDto);

        mockMvc.perform(get("/trainers/by-email/{email}", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value(email));

        verify(trainerService, times(1)).findByEmail(email);
    }

    @Test
    public void findTrainerByPhoneTest() throws Exception {
        String phone = "+79995552255";

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setPhoneNumber(phone);

        when(trainerService.findByPhone(phone)).thenReturn(trainerResponseDto);

        mockMvc.perform(get("/trainers/by-phoneNumber/{phone}", phone))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.phoneNumber").value(phone));

        verify(trainerService, times(1)).findByPhone(phone);
    }

    @Test
    public void findTrainersBySpecializationTest() throws Exception {
        String specialization = "Бокс";

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setSpecialization(specialization);

        TrainerResponseDto trainerResponseDto2 = new TrainerResponseDto();
        trainerResponseDto2.setId(2L);
        trainerResponseDto2.setSpecialization(specialization);

        List<TrainerResponseDto> trainerResponseDtoList = new ArrayList<>();
        trainerResponseDtoList.add(trainerResponseDto);
        trainerResponseDtoList.add(trainerResponseDto2);

        when(trainerService.findBySpecialization(specialization)).thenReturn(trainerResponseDtoList);

        mockMvc.perform(get("/trainers/by-specialization/{specialization}", specialization))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainerService, times(1)).findBySpecialization(specialization);
    }

    @Test
    public void findActiveTrainersTest() throws Exception {
        boolean isActive = true;

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setActive(isActive);

        TrainerResponseDto trainerResponseDto2 = new TrainerResponseDto();
        trainerResponseDto2.setId(2L);
        trainerResponseDto2.setActive(isActive);

        List<TrainerResponseDto> trainerResponseDtoList = new ArrayList<>();
        trainerResponseDtoList.add(trainerResponseDto);
        trainerResponseDtoList.add(trainerResponseDto2);

        when(trainerService.findByActiveTrue()).thenReturn(trainerResponseDtoList);

        mockMvc.perform(get("/trainers/activeTrainers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainerService, times(1)).findByActiveTrue();
    }

    @Test
    public void findUnactiveTrainersTest() throws Exception {
        boolean isActive = false;

        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);
        trainerResponseDto.setActive(isActive);

        TrainerResponseDto trainerResponseDto2 = new TrainerResponseDto();
        trainerResponseDto2.setId(2L);
        trainerResponseDto2.setActive(isActive);

        List<TrainerResponseDto> trainerResponseDtoList = new ArrayList<>();
        trainerResponseDtoList.add(trainerResponseDto);
        trainerResponseDtoList.add(trainerResponseDto2);

        when(trainerService.findByActiveFalse()).thenReturn(trainerResponseDtoList);

        mockMvc.perform(get("/trainers/unactiveTrainers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainerService, times(1)).findByActiveFalse();
    }

    @Test
    public void findAllTrainersTest() throws Exception {
        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(1L);

        TrainerResponseDto trainerResponseDto2 = new TrainerResponseDto();
        trainerResponseDto2.setId(2L);

        List<TrainerResponseDto> trainerResponseDtoList = new ArrayList<>();
        trainerResponseDtoList.add(trainerResponseDto);
        trainerResponseDtoList.add(trainerResponseDto2);

        when(trainerService.showAllTrainers()).thenReturn(trainerResponseDtoList);

        mockMvc.perform(get("/trainers/allTrainers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainerService, times(1)).showAllTrainers();
    }

    @Test
    public void updateTrainerTest() throws Exception {
        long trainerId = 1L;

        UpdateTrainerDto updateTrainerDto = new UpdateTrainerDto();
        updateTrainerDto.setFirstName("Иван");
        updateTrainerDto.setLastName("Иванов");
        updateTrainerDto.setPhoneNumber("+78885556644");
        updateTrainerDto.setSpecialization("Танцы");
        updateTrainerDto.setActive(false);

        mockMvc.perform(put("/trainers/put/{trainerId}", trainerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTrainerDto)))
                .andExpect(status().isOk());

        verify(trainerService, times(1)).updateInfoAboutTrainer(eq(trainerId), any(UpdateTrainerDto.class));
    }

    @Test
    public void deleteTrainerTest() throws Exception {
        long trainerId = 1L;

        mockMvc.perform(delete("/trainers/delete/{trainerId}", trainerId))
                .andExpect(status().isOk());

        verify(trainerService, times(1)).deleteTrainer(trainerId);
    }
}
