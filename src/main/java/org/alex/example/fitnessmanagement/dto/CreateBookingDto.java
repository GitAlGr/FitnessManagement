package org.alex.example.fitnessmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingDto {

    @NotNull(message = "Id клиента обязательно")
    @Positive(message = "Id клиента не может иметь отрицательное значение")
    private Long clientId;

    @NotNull(message = "Id тренировки обязательно")
    @Positive(message = "Id тренировки не может иметь отрицательное значение")
    private Long trainingSessionId;

    public CreateBookingDto() {
    }

    public CreateBookingDto(Long clientId, Long trainingSessionId) {
        this.clientId = clientId;
        this.trainingSessionId = trainingSessionId;
    }

    @Override
    public String toString() {
        return "CreateBookingDto{" +
                "clientId=" + clientId +
                ", trainingSessionId=" + trainingSessionId +
                '}';
    }
}
