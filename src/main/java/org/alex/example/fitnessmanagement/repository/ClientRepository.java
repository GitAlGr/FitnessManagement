package org.alex.example.fitnessmanagement.repository;

import org.alex.example.fitnessmanagement.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByFirstName(String firstName);

    Optional<Client> findByLastName(String lastName);

    Optional<Client> findByEmail(String email);

    Optional<Client> findByPhone(String phone);

    List<Client> findByIsActiveTrue();

    List<Client> findByIsActiveFalse();

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
