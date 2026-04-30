package org.alex.example.fitnessmanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UpdateMembershipDto {

    private LocalDate endDate;
    private Boolean active;
    private Integer remainingVisits;
}

