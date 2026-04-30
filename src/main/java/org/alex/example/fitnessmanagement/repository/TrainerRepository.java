package org.alex.example.fitnessmanagement.repository;

import org.alex.example.fitnessmanagement.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Optional<Trainer> findByFirstName(String firstName);

    Optional<Trainer> findByLastName(String lastName);

    Optional<Trainer> findByEmail(String email);

    Optional<Trainer> findByPhone(String phone);

    List<Trainer> findBySpecialization(String specialization);

    List<Trainer> findByActiveTrue();

    List<Trainer> findByActiveFalse();

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
