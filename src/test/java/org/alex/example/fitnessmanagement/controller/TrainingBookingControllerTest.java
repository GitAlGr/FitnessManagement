package org.alex.example.fitnessmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alex.example.fitnessmanagement.dto.BookingResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateBookingDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTrainingBooking;
import org.alex.example.fitnessmanagement.service.TrainingBookingService;
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

@WebMvcTest(TrainingBookingController.class)
public class TrainingBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingBookingService trainingBookingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addTrainingBookingTest() throws Exception {
        CreateBookingDto createBookingDto = new CreateBookingDto();
        createBookingDto.setClientId(1L);
        createBookingDto.setTrainingSessionId(1L);

        mockMvc.perform(post("/trainingBookings/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBookingDto)))
                .andExpect(status().isOk());

        verify(trainingBookingService, times(1)).add(any(CreateBookingDto.class));
    }

    @Test
    public void findBookingByIdTest() throws Exception {
        long id = 1L;

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(id);

        when(trainingBookingService.findBookingById(id)).thenReturn(bookingResponseDto);

        mockMvc.perform(get("/trainingBookings/by-trainingBookingId/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

        verify(trainingBookingService, times(1)).findBookingById(id);
    }

    @Test
    public void findBookingByClientIdTest() throws Exception {
        long clientId = 1L;

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(1L);
        bookingResponseDto.setClientId(clientId);

        BookingResponseDto bookingResponseDto2 = new BookingResponseDto();
        bookingResponseDto2.setId(2L);
        bookingResponseDto2.setClientId(clientId);

        List<BookingResponseDto> list = new ArrayList<>();
        list.add(bookingResponseDto);
        list.add(bookingResponseDto2);

        when(trainingBookingService.findByClientId(clientId)).thenReturn(list);

        mockMvc.perform(get("/trainingBookings/by-clientId/{id}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainingBookingService, times(1)).findByClientId(clientId);
    }

    @Test
    public void findBookingByTrainingSessionIdTest() throws Exception {
        long trainingSessionId = 1L;

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(1L);
        bookingResponseDto.setTrainingSessionId(trainingSessionId);

        BookingResponseDto bookingResponseDto2 = new BookingResponseDto();
        bookingResponseDto2.setId(2L);
        bookingResponseDto2.setTrainingSessionId(trainingSessionId);

        List<BookingResponseDto> list = new ArrayList<>();
        list.add(bookingResponseDto);
        list.add(bookingResponseDto2);

        when(trainingBookingService.findByTrainingSessionId(trainingSessionId)).thenReturn(list);

        mockMvc.perform(get("/trainingBookings/by-trainingSessionId/{id}", trainingSessionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainingBookingService, times(1)).findByTrainingSessionId(trainingSessionId);
    }

    @Test
    public void findBookingByStatusTest() throws Exception {
        StatusOfTrainingBooking status = StatusOfTrainingBooking.CANCELLED;

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(1L);
        bookingResponseDto.setStatus(status);

        BookingResponseDto bookingResponseDto2 = new BookingResponseDto();
        bookingResponseDto2.setId(2L);
        bookingResponseDto2.setStatus(status);

        List<BookingResponseDto> list = new ArrayList<>();
        list.add(bookingResponseDto);
        list.add(bookingResponseDto2);

        when(trainingBookingService.findByStatus(status)).thenReturn(list);

        mockMvc.perform(get("/trainingBookings/by-status/{status}", status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainingBookingService, times(1)).findByStatus(status);
    }

    @Test
    public void findAllTest() throws Exception {
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(1L);

        BookingResponseDto bookingResponseDto2 = new BookingResponseDto();
        bookingResponseDto2.setId(2L);

        List<BookingResponseDto> list = new ArrayList<>();
        list.add(bookingResponseDto);
        list.add(bookingResponseDto2);

        when(trainingBookingService.showAll()).thenReturn(list);

        mockMvc.perform(get("/trainingBookings/allTrainingBookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(trainingBookingService, times(1)).showAll();
    }

    @Test
    public void cancelTrainingBookingTest() throws Exception {
        long id = 1L;

        mockMvc.perform(put("/trainingBookings/cancel/{id}", id))
                .andExpect(status().isOk());

        verify(trainingBookingService, times(1)).cancel(id);
    }

    @Test
    public void markAttendedTrainingBookingTest() throws Exception {
        long id = 1L;

        mockMvc.perform(put("/trainingBookings/markAttended/{id}", id))
                .andExpect(status().isOk());

        verify(trainingBookingService, times(1)).markAttended(id);
    }

    @Test
    public void markNoShowTrainingBookingTest() throws Exception {
        long id = 1L;

        mockMvc.perform(put("/trainingBookings/markNoShow/{id}", id))
                .andExpect(status().isOk());

        verify(trainingBookingService, times(1)).markNoShow(id);
    }

    @Test
    public void deleteTrainingBookingTest() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/trainingBookings/delete/{id}", id))
                .andExpect(status().isOk());

        verify(trainingBookingService, times(1)).delete(id);
    }
}
