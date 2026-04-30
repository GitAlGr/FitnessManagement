package org.alex.example.fitnessmanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.alex.example.fitnessmanagement.entity.TypeOfTrainingSession;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
public class CreateTrainingSessionDto {

    @NotNull(message = "Данное поле не должно быть пустым")
    @Positive
    private Long trainerId;

    @NotBlank(message = "Название тренировки обязательно")
    private String name;

    @NotNull(message = "Тип тренировки обязательно должен быть указан. Групповая или индивидуальная")
    private TypeOfTrainingSession type;

    @NotNull(message = "Данное поле не должно быть пустым")
    @FutureOrPresent
    private LocalDate date;

    @NotNull(message = "Данное поле не должно быть пустым")
    private LocalTime startTime;

    @Positive
    @Min(30)
    @Max(180)
    private Integer durationMinutes;

    @Positive
    @Min(1)
    @Max(50)
    private Integer maxParticipants;

    public CreateTrainingSessionDto() {
    }

        public CreateTrainingSessionDto(Long trainerId, String name, TypeOfTrainingSession type, LocalDate date, LocalTime startTime, Integer durationMinutes, Integer maxParticipants) {
        this.trainerId = trainerId;
        this.name = name;
        this.type = type;
        this.date = date;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.maxParticipants = maxParticipants;
    }

    @Override
    public String toString() {
        return "CreateTrainingSessionDto{" +
                "trainerId=" + trainerId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", date=" + date +
                ", startTime=" + startTime +
                ", durationMinutes=" + durationMinutes +
                ", maxParticipants=" + maxParticipants +
                '}';
    }
}
