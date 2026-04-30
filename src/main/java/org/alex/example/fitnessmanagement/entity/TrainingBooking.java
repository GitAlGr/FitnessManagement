package org.alex.example.fitnessmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "training_Bookings")
@Getter
@Setter
public class TrainingBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "trainingSession_id")
    private TrainingSession trainingSession;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "attended")
    private boolean attended;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusOfTrainingBooking status;

    public TrainingBooking() {
    }

    public TrainingBooking(Client client, TrainingSession trainingSession, LocalDateTime bookingDate, boolean attended, StatusOfTrainingBooking status) {
        this.client = client;
        this.trainingSession = trainingSession;
        this.bookingDate = bookingDate;
        this.attended = attended;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TrainingBooking{" +
                "id=" + id +
                ", bookingDate=" + bookingDate +
                ", attended=" + attended +
                ", status=" + status +
                '}';
    }
}
