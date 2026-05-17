package org.alex.example.fitnessmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alex.example.fitnessmanagement.dto.CreateTrainingSessionDto;
import org.alex.example.fitnessmanagement.dto.TrainingSessionResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainingSessionDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTraining;
import org.alex.example.fitnessmanagement.entity.TypeOfTrainingSession;
import org.alex.example.fitnessmanagement.service.TrainingSessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainingSessionController.class)
public class TrainingSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingSessionService trainingSessionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addTrainingSessionTest() throws Exception {
        CreateTrainingSessionDto createTrainingSessionDto = new CreateTrainingSessionDto();
        createTrainingSessionDto.setName("Бокс");
        createTrainingSessionDto.setType(TypeOfTrainingSession.PERSONAL);
        createTrainingSessionDto.setDate(LocalDate.of(2026, 5, 20));
        createTrainingSessionDto.setStartTime(LocalTime.of(12, 10));
        createTrainingSessionDto.setTrainerId(1L);
        createTrainingSessionDto.setDurationMinutes(120);
        createTrainingSessionDto.setMaxParticipants(1);

        mockMvc.perform(post("/trainingSessions/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTrainingSessionDto)))
                .andExpect(status().isOk());

        verify(trainingSessionService, times(1)).add(any(CreateTrainingSessionDto.class));
    }

    @Test
    public void findByIdTest() throws Exception {
        long id = 1L;

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(id);

        when(trainingSessionService.findById(id)).thenReturn(trainingSessionResponseDto);

        mockMvc.perform(get("/trainingSessions/by-trainingSessionId/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

        verify(trainingSessionService, times(1)).findById(id);
    }

    @Test
    public void findByTrainerIdTest() throws Exception {
        long trainerId = 1L;

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);
        trainingSessionResponseDto.setTrainerId(trainerId);

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);
        trainingSessionResponseDto2.setTrainerId(trainerId);

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionService.findByTrainerId(trainerId)).thenReturn(trainingSessionResponseDtoList);

        mockMvc.perform(get("/trainingSessions/by-trainer/{id}", trainerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainingSessionService, times(1)).findByTrainerId(trainerId);
    }

    @Test
    public void findByDateTest() throws Exception {
        LocalDate date = LocalDate.of(2026, 5, 5);

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);
        trainingSessionResponseDto.setDate(date);

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);
        trainingSessionResponseDto2.setDate(date);

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionService.findByDate(date)).thenReturn(trainingSessionResponseDtoList);

        mockMvc.perform(get("/trainingSessions/by-date/{date}", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainingSessionService, times(1)).findByDate(date);
    }

    @Test
    public void findByDateBetweenTest() throws Exception {
        LocalDate start = LocalDate.of(2026, 5, 1);
        LocalDate end = LocalDate.of(2026, 5, 31);

        TrainingSessionResponseDto dto = new TrainingSessionResponseDto();
        dto.setId(1L);
        dto.setDate(start.plusDays(5));

        List<TrainingSessionResponseDto> list = List.of(dto);

        when(trainingSessionService.findByDateBetween(start, end)).thenReturn(list);

        mockMvc.perform(get("/trainingSessions/by-dateBetween")
                        .param("start", start.toString())
                        .param("end", end.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].id").value(1));

        verify(trainingSessionService).findByDateBetween(start, end);
    }

    @Test
    public void findByStatusTest() throws Exception {
        StatusOfTraining status = StatusOfTraining.COMPLETED;

        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);
        trainingSessionResponseDto.setStatus(status);

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);
        trainingSessionResponseDto2.setStatus(status);

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionService.findByStatus(status)).thenReturn(trainingSessionResponseDtoList);

        mockMvc.perform(get("/trainingSessions/by-status/{status}", status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));
        verify(trainingSessionService, times(1)).findByStatus(status);
    }

    @Test
    public void findAllTest() throws Exception {
        TrainingSessionResponseDto trainingSessionResponseDto = new TrainingSessionResponseDto();
        trainingSessionResponseDto.setId(1L);

        TrainingSessionResponseDto trainingSessionResponseDto2 = new TrainingSessionResponseDto();
        trainingSessionResponseDto2.setId(2L);

        List<TrainingSessionResponseDto> trainingSessionResponseDtoList = new ArrayList<>();
        trainingSessionResponseDtoList.add(trainingSessionResponseDto);
        trainingSessionResponseDtoList.add(trainingSessionResponseDto2);

        when(trainingSessionService.showAll()).thenReturn(trainingSessionResponseDtoList);

        mockMvc.perform(get("/trainingSessions/allTrainingSessions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));
        verify(trainingSessionService, times(1)).showAll();
    }

    @Test
    public void updateTrainingSessionTest() throws Exception {
        long id = 1L;

        UpdateTrainingSessionDto updateTrainingSessionDto = new UpdateTrainingSessionDto();
        updateTrainingSessionDto.setName("Танцы");
        updateTrainingSessionDto.setDate(LocalDate.of(2026, 6, 6));
        updateTrainingSessionDto.setStatus(StatusOfTraining.CANCELLED);
        updateTrainingSessionDto.setMaxParticipants(15);
        updateTrainingSessionDto.setStartTime(LocalTime.of(15, 10));
        updateTrainingSessionDto.setDurationMinutes(120);

        mockMvc.perform(put("/trainingSessions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTrainingSessionDto)))
                .andExpect(status().isOk());
        verify(trainingSessionService, times(1)).updateInfoAboutTrainingSession(eq(id), any(UpdateTrainingSessionDto.class));
    }

    @Test
    public void deleteTrainingSessionTest() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/trainingSessions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(trainingSessionService, times(1)).deleteTrainingSession(id);
    }
}