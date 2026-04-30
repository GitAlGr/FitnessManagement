package org.alex.example.fitnessmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.alex.example.fitnessmanagement.entity.StatusOfTrainingBooking;

@Getter
@Setter
public class UpdateBookingStatusDto {

    @NotNull
    private StatusOfTrainingBooking status;
    @Size(max = 500, message = "Причина отмены не должна превышать 500 символов")
    private String cancellationReason;

    public UpdateBookingStatusDto() {
    }

    public UpdateBookingStatusDto(StatusOfTrainingBooking status, String cancellationReason) {
        this.status = status;
        this.cancellationReason = cancellationReason;
    }

    @Override
    public String toString() {
        return "UpdateBookingStatusDto{" +
                "status=" + status +
                ", cancellationReason='" + cancellationReason + '\'' +
                '}';
    }
}
