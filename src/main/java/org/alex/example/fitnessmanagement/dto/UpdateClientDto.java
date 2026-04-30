package org.alex.example.fitnessmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateClientDto {

    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    private String firstName;

    @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
    private String lastName;

    @Pattern(regexp = "^(\\+7|8)\\d{10}$", message = "Телефон должен быть в формате +7XXXXXXXXXX или 8XXXXXXXXXX")
    private String phoneNumber;

    private Boolean active;

    public UpdateClientDto(String firstName, String lastName, String phoneNumber, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    @Override
    public String toString() {
        return "UpdateClientDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                '}';
    }
}
