package org.alex.example.fitnessmanagement.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.alex.example.fitnessmanagement.entity.StatusOfTraining;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class UpdateTrainingSessionDto {

    private String name;
    @FutureOrPresent
    private LocalDate date;
    private LocalTime startTime;
    private Integer durationMinutes;
    private Integer maxParticipants;
    private StatusOfTraining status;

    public UpdateTrainingSessionDto() {
    }

    public UpdateTrainingSessionDto(String name, LocalDate date, LocalTime startTime, Integer durationMinutes, Integer maxParticipants, StatusOfTraining status) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.maxParticipants = maxParticipants;
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateTrainingSessionDto{" +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", durationMinutes=" + durationMinutes +
                ", maxParticipants=" + maxParticipants +
                ", status=" + status +
                '}';
    }
}
