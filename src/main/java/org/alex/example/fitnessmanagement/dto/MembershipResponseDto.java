package org.alex.example.fitnessmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResponseDto {

    private long id;
    private long clientId;
    private String clientFullName;
    private TypeOfMembership type;
    private LocalDate purchaseDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private Boolean active;
    private Integer remainingVisits;

    @Override
    public String toString() {
        return "MembershipResponseDto{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", clientFullName='" + clientFullName + '\'' +
                ", type=" + type +
                ", purchaseDate=" + purchaseDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", active=" + active +
                ", remainingVisits=" + remainingVisits +
                '}';
    }
}
