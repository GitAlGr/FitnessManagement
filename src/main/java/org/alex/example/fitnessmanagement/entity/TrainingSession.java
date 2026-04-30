package org.alex.example.fitnessmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "training_Sessions")
@Getter
@Setter
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeOfTrainingSession type;

    @Column(name = "date")
    private LocalDate dateOfTrainingSession;

    @Column(name = "start_time")
    private LocalTime timeOfBeginTraining;

    @Column(name = "duration_minutes")
    private Integer durationOfTrainingInMinutes;

    @Column(name = "max_participants")
    private Integer maxQuantityOfParticipants;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusOfTraining status;

    public TrainingSession() {
    }

    public TrainingSession(Trainer trainer, String name, TypeOfTrainingSession type, LocalDate dateOfTrainingSession, LocalTime timeOfBeginTraining, Integer durationOfTrainingInMinutes, Integer maxQuantityOfParticipants, StatusOfTraining status) {
        this.trainer = trainer;
        this.name = name;
        this.type = type;
        this.dateOfTrainingSession = dateOfTrainingSession;
        this.timeOfBeginTraining = timeOfBeginTraining;
        this.durationOfTrainingInMinutes = durationOfTrainingInMinutes;
        this.maxQuantityOfParticipants = maxQuantityOfParticipants;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", dateOfTrainingSession=" + dateOfTrainingSession +
                ", timeOfBeginTraining=" + timeOfBeginTraining +
                ", durationOfTrainingInMinutes=" + durationOfTrainingInMinutes +
                ", maxQuantityOfParticipants=" + maxQuantityOfParticipants +
                ", status=" + status +
                '}';
    }
}
