package org.alex.example.fitnessmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "memberships")
@Getter
@Setter
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "type_of_membership")
    @Enumerated(EnumType.STRING)
    private TypeOfMembership type;

    @Column(name = "date_of_purchase")
    private LocalDate purchaseDate;

    @Column(name = "date_of_start")
    private LocalDate startDate;

    @Column(name = "date_of_end")
    private LocalDate endDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "remaining_visits")
    private Integer remainingVisits;

    public Membership() {
    }

    public Membership(Client client, TypeOfMembership type, LocalDate purchaseDate, LocalDate startDate, LocalDate endDate, BigDecimal price, boolean isActive, Integer remainingVisits) {
        this.client = client;
        this.type = type;
        this.purchaseDate = purchaseDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.isActive = isActive;
        this.remainingVisits = remainingVisits;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", type=" + type +
                ", purchaseDate=" + purchaseDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", isActive=" + isActive +
                ", remainingVisits=" + remainingVisits +
                '}';
    }
}
