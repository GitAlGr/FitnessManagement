package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.CreateMembershipDto;
import org.alex.example.fitnessmanagement.dto.MembershipResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateMembershipDto;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;

import java.time.LocalDate;
import java.util.List;

public interface MembershipService {

    void add(CreateMembershipDto createMembershipDto);

    MembershipResponseDto findById(long id);

    List<MembershipResponseDto> findByClientId(Long clientId);

    MembershipResponseDto findByClientIdAndActiveTrue(Long clientId);

    List<MembershipResponseDto> findByActiveTrueAndEndDateBefore(LocalDate date);

    List<MembershipResponseDto> findByType(TypeOfMembership type);

    List<MembershipResponseDto> showAll();

    void updateInfoAboutMembership(Long membershipId, UpdateMembershipDto updateMembershipDto);

    void deleteMembership(Long membershipId);

}
