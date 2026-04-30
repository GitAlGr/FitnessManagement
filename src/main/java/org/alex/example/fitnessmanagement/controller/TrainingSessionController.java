package org.alex.example.fitnessmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alex.example.fitnessmanagement.dto.CreateTrainingSessionDto;
import org.alex.example.fitnessmanagement.dto.TrainingSessionResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainingSessionDto;
import org.alex.example.fitnessmanagement.entity.StatusOfTraining;
import org.alex.example.fitnessmanagement.service.TrainingSessionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainingSessions")
public class TrainingSessionController {

    private final TrainingSessionService trainingSessionService;

    @PostMapping("/add")
    @Operation(summary = "Добавление тренировочной сессии", description = "Регистрация тренировочной сессии в базе фитнес клуба")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запись тренировки успешно добавлена"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void add(@Valid @RequestBody CreateTrainingSessionDto createTrainingSessionDto) {
        trainingSessionService.add(createTrainingSessionDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск тренировочной сессии", description = "Поиск тренировочной сессии в базе фитнес клуба по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запись тренировки успешно найдена"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public TrainingSessionResponseDto findById(@Valid @PathVariable long id) {
        return trainingSessionService.findById(id);
    }

    @GetMapping("/by-trainer/{trainerId}")
    @Operation(summary = "Поиск тренировочных сессий", description = "Поиск тренировочных сессий в базе фитнес клуба по id тренера")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи тренировок успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<TrainingSessionResponseDto> findByTrainerId(@Valid @PathVariable long trainerId) {
        return trainingSessionService.findByTrainerId(trainerId);
    }

    @GetMapping("/by-date/{date}")
    @Operation(summary = "Поиск тренировочных сессий", description = "Поиск тренировочных сессий в базе фитнес клуба по дате")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи тренировок успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<TrainingSessionResponseDto> findByDate(@Valid @PathVariable LocalDate date) {
        return trainingSessionService.findByDate(date);
    }

    @GetMapping("/by-dateBetween")
    @Operation(summary = "Поиск тренировочных сессий", description = "Поиск тренировочных сессий в базе фитнес клуба в промежутке двух дат")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи тренировок успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<TrainingSessionResponseDto> findByDateBetween(@Valid @RequestParam LocalDate start, @RequestParam LocalDate end) {
        return trainingSessionService.findByDateBetween(start, end);
    }

    @GetMapping("/by-status/{status}")
    @Operation(summary = "Поиск тренировочных сессий", description = "Поиск тренировочных сессий в базе фитнес клуба по статусу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи тренировок успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<TrainingSessionResponseDto> findByStatus(@Valid @PathVariable StatusOfTraining status) {
        return trainingSessionService.findByStatus(status);
    }

    @GetMapping("/allTrainingSessions")
    @Operation(summary = "Поиск тренировочных сессий", description = "Поиск всех тренировочных сессий в базе фитнес клуба")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи тренировок успешно найдены"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<TrainingSessionResponseDto> findAll() {
        return trainingSessionService.showAll();
    }

    @PutMapping("/{trainingSessionId}")
    @Operation(summary = "Изменение данных", description = "Изменение данных тренировочной сессии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи тренировок успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void updateTrainingSession(@Valid @PathVariable long trainingSessionId, @RequestBody UpdateTrainingSessionDto updateTrainingSessionDto) {
        trainingSessionService.updateInfoAboutTrainingSession(trainingSessionId, updateTrainingSessionDto);
    }

    @DeleteMapping("/{trainingSessionId}")
    @Operation(summary = "Удаление", description = "Удаление тренировочной сессии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Записи тренировок успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void deleteTrainingSession(@Valid @PathVariable long trainingSessionId) {
        trainingSessionService.deleteTrainingSession(trainingSessionId);
    }
}
