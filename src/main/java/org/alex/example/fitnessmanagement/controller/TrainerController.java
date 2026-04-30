package org.alex.example.fitnessmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alex.example.fitnessmanagement.dto.CreateTrainerDto;
import org.alex.example.fitnessmanagement.dto.TrainerResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateTrainerDto;
import org.alex.example.fitnessmanagement.service.TrainerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping("/add")
    @Operation(summary = "Добавление тренера", description = "Регистрация тренера в базе фитнес клуба")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренер успешно добавлен"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void addTrainer(@Valid @RequestBody CreateTrainerDto createTrainerDto) {
        trainerService.add(createTrainerDto);
    }

    @GetMapping("/{trainerId}")
    @Operation(summary = "Поиск тренера", description = "Поиск тренера в базе по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренер успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public TrainerResponseDto findTrainerById(@Valid @PathVariable Long trainerId) {
        return trainerService.findById(trainerId);
    }

    @GetMapping("/by-firstName/{firstName}")
    @Operation(summary = "Поиск тренера", description = "Поиск тренера в базе по имени")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренер успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public TrainerResponseDto findTrainerByFirstName(@Valid @PathVariable String firstName) {
        return trainerService.findByFirstName(firstName);
    }

    @GetMapping("/by-lastName/{lastName}")
    @Operation(summary = "Поиск тренера", description = "Поиск тренера в базе по фамилии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренер успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public TrainerResponseDto findTrainerByLastName(@Valid @PathVariable String lastName) {
        return trainerService.findByLastName(lastName);
    }

    @GetMapping("/by-email/{email}")
    @Operation(summary = "Поиск тренера", description = "Поиск тренера в базе по email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренер успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public TrainerResponseDto findTrainerByEmail(@Valid @PathVariable String email) {
        return trainerService.findByEmail(email);
    }

    @GetMapping("/by-phoneNumber/{phone}")
    @Operation(summary = "Поиск тренера", description = "Поиск тренера в базе по номеру телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренер успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public TrainerResponseDto findTrainerByPhone(@Valid @PathVariable String phone) {
        return trainerService.findByPhone(phone);
    }

    @GetMapping("/by-specialization/{specialization}")
    @Operation(summary = "Поиск тренеров", description = "Поиск тренеров в базе по специальности")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренера успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<TrainerResponseDto> findTrainerBySpecialization(@Valid @PathVariable String specialization) {
        return trainerService.findBySpecialization(specialization);
    }


    @GetMapping("/activeTrainers")
    @Operation(summary = "Поиск тренеров", description = "Поиск активных тренеров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренера успешно найдены"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<TrainerResponseDto> findActiveTrainers() {
        return trainerService.findByActiveTrue();
    }


    @GetMapping("/unactiveTrainers")
    @Operation(summary = "Поиск тренеров", description = "Поиск неактивных тренеров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренера успешно найдены"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<TrainerResponseDto> findUnactiveTrainers() {
        return trainerService.findByActiveFalse();
    }


    @GetMapping("/allTrainers")
    @Operation(summary = "Поиск тренеров", description = "Поиск всех тренеров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренера успешно найдены"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<TrainerResponseDto> findAllTrainers() {
        return trainerService.showAllTrainers();
    }

    @PutMapping("/put/{trainerId}")
    @Operation(summary = "Изменение данных", description = "Изменение данных тренера")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные тренера успешно изменены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void updateTrainer(@Valid @PathVariable Long trainerId, @RequestBody UpdateTrainerDto updateTrainerDto) {
        trainerService.updateInfoAboutTrainer(trainerId, updateTrainerDto);
    }

    @DeleteMapping("/delete/{trainerId}")
    @Operation(summary = "Удаление", description = "Удаление тренера из базы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тренер успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void deleteTrainer(@Valid @PathVariable Long trainerId) {
        trainerService.deleteTrainer(trainerId);
    }
}
