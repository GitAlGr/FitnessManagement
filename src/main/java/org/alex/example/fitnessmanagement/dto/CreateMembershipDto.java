package org.alex.example.fitnessmanagement.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;

import java.time.LocalDate;

@Getter
@Setter
public class CreateMembershipDto {

    @NotNull(message = "Id клиента обязательно")
    @Positive(message = "Id клиента должен быть положительным")
    private Long clientId;

    @NotNull(message = "Тип абонимента обязателен")
    private TypeOfMembership type;

    public CreateMembershipDto() {
    }

    public CreateMembershipDto(Long clientId, TypeOfMembership type) {
        this.clientId = clientId;
        this.type = type;
    }

    @Override
    public String toString() {
        return "CreateMembershipDto{" +
                "clientId=" + clientId +
                ", type=" + type +
                '}';
    }
}
