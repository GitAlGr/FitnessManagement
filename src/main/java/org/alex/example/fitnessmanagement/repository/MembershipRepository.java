package org.alex.example.fitnessmanagement.repository;

import org.alex.example.fitnessmanagement.entity.Membership;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByClientId(Long clientId);

    Optional<Membership> findByClientIdAndIsActiveTrue(Long clientId);

    List<Membership> findByIsActiveTrueAndEndDateBefore(LocalDate date);

    List<Membership> findByType(TypeOfMembership type);
}