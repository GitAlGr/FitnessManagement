package org.alex.example.fitnessmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alex.example.fitnessmanagement.dto.BookingResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateBookingDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTrainingBooking;
import org.alex.example.fitnessmanagement.service.TrainingBookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainingBookings")
public class TrainingBookingController {

    private final TrainingBookingService trainingBookingService;

    @PostMapping("/add")
    @Operation(summary = "Добавление записи на тренировку", description = "Добавление записи на тренировку в базе фитнес клуба")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запись на тренировку успешно добавлена"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void add(@Valid @RequestBody CreateBookingDto createBookingDto) {
        trainingBookingService.add(createBookingDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск записи на тренировку", description = "Поиск записи на тренировку в базе фитнес клуба по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запись на тренировку успешно найдена"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public BookingResponseDto findBookingById(@PathVariable long id) {
        return trainingBookingService.findBookingById(id);
    }

    @GetMapping("/by-clientId/{id}")
    @Operation(summary = "Поиск записей на тренировки", description = "Поиск записей на тренировки в базе фитнес клуба по id клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи на тренировки успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<BookingResponseDto> findBookingByClientId(@PathVariable long id) {
        return trainingBookingService.findByClientId(id);
    }

    @GetMapping("/by-trainingSessionId/{id}")
    @Operation(summary = "Поиск записей на тренировки", description = "Поиск записей на тренировки в базе фитнес клуба по id тренировочных сессий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи на тренировки успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<BookingResponseDto> findBookingByTrainingSessionId(@PathVariable long id) {
        return trainingBookingService.findByTrainingSessionId(id);
    }

    @GetMapping("/by-status/{status}")
    @Operation(summary = "Поиск записей на тренировки", description = "Поиск записей на тренировки в базе фитнес клуба по статусу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи на тренировки успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<BookingResponseDto> findBookingByStatus(@Valid @PathVariable StatusOfTrainingBooking status) {
        return trainingBookingService.findByStatus(status);
    }

    @GetMapping("/allTrainingBookings")
    @Operation(summary = "Поиск записей на тренировки", description = "Поиск всех записей на тренировки в базе фитнес клуба")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи на тренировки успешно найдены"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<BookingResponseDto> findAll() {
        return trainingBookingService.showAll();
    }

    @PutMapping("/cancel/{id}")
    @Operation(summary = "Отмена", description = "Отменить запись на тренировку")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запись на тренировку успешно отменена"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void cancel(@PathVariable long id) {
        trainingBookingService.cancel(id);
    }

    @PutMapping("/markAttended/{id}")
    @Operation(summary = "Посещение", description = "Клиент посетил тренировку")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренировка успешно прошла"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void markAttended(@PathVariable long id) {
        trainingBookingService.markAttended(id);
    }

    @PutMapping("/markNoShow/{id}")
    @Operation(summary = "Не явился", description = "Клиент не явился на тренировку")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент не явился на тренировку"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void markNoShow(@PathVariable long id) {
        trainingBookingService.markNoShow(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление", description = "Удаление записи на тренировку")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запись на тренировку успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void delete(@PathVariable long id) {
        trainingBookingService.delete(id);
    }
}
