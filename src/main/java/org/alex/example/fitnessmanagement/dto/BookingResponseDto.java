package org.alex.example.fitnessmanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.alex.example.fitnessmanagement.entity.StatusOfTrainingBooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class BookingResponseDto {

    private long id;
    private long clientId;
    private String clientFullName;
    private long trainingSessionId;
    private String trainingSessionName;
    private LocalDate trainingDate;
    private LocalTime trainingStartTime;
    private String trainerFullName;
    private LocalDateTime bookingDateTime;
    private Boolean attended;
    private StatusOfTrainingBooking status;
}
