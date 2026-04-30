package org.alex.example.fitnessmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Boolean active;
    private TypeOfMembership activeMembershipType;
    private LocalDate activeMembershipEndDate;
    private LocalDate registrationDate;

    @Override
    public String toString() {
        return "ClientResponseDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", active=" + active +
                ", activeMembershipType=" + activeMembershipType +
                ", activeMembershipEndDate=" + activeMembershipEndDate +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
