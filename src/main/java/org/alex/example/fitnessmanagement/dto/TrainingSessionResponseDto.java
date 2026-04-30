package org.alex.example.fitnessmanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.alex.example.fitnessmanagement.entity.StatusOfTraining;
import org.alex.example.fitnessmanagement.entity.TypeOfTrainingSession;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TrainingSessionResponseDto {

    private long id;
    private long trainerId;
    private String trainerFullName;
    private String name;
    private TypeOfTrainingSession type;
    private LocalDate date;
    private LocalTime startTime;
    private int durationMinutes;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private StatusOfTraining status;
    private Boolean available;

}
