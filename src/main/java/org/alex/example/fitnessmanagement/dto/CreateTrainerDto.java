package org.alex.example.fitnessmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTrainerDto {

    @NotBlank(message = "Имя обязательно!")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна!")
    @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
    private String lastName;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email обязателен")
    private String email;

    @Pattern(regexp = "^(\\+7|8)\\d{10}$", message = "Телефон должен быть в формате +7XXXXXXXXXX или 8XXXXXXXXXX")
    private String phoneNumber;

    @NotBlank(message = "Специализация обязательна. Бокс, фитнес, йога, кроссфит или плаванье")
    private String specialization;

    public CreateTrainerDto() {
    }

    public CreateTrainerDto(String firstName, String lastName, String email, String phoneNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "CreateTrainerDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
