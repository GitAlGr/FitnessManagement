package org.alex.example.fitnessmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainers")
@Getter
@Setter
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingSession> trainingSessions = new ArrayList<>();

    public Trainer() {
    }

    public Trainer(String firstName, String lastName, String email, String phone, String specialization, LocalDate hireDate, boolean active, List<TrainingSession> trainingSessions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
        this.hireDate = hireDate;
        this.active = active;
        this.trainingSessions = trainingSessions;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", specialization='" + specialization + '\'' +
                ", hireDate=" + hireDate +
                ", active=" + active +
                '}';
    }
}
