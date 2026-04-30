package org.alex.example.fitnessmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerResponseDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String specialization;
    private LocalDate hireDate;
    private Boolean active;
    private Integer upcomingSessionsCount;

    @Override
    public String toString() {
        return "TrainerResponseDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", specialization='" + specialization + '\'' +
                ", hireDate=" + hireDate +
                ", active=" + active +
                ", upcomingSessionsCount=" + upcomingSessionsCount +
                '}';
    }
}
