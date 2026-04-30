package org.alex.example.fitnessmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alex.example.fitnessmanagement.dto.CreateMembershipDto;
import org.alex.example.fitnessmanagement.dto.MembershipResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateMembershipDto;
import org.alex.example.fitnessmanagement.entity.Membership;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;
import org.alex.example.fitnessmanagement.error.NotFoundByIdException;
import org.alex.example.fitnessmanagement.mapper.MembershipMapper;
import org.alex.example.fitnessmanagement.repository.MembershipRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;

    @Override
    public void add(CreateMembershipDto createMembershipDto) {
        log.info("Добавление абонимента: {}", createMembershipDto);
        Membership membership = membershipMapper.toEntity(createMembershipDto);
        membershipRepository.save(membership);
    }

    @Override
    public MembershipResponseDto findById(long id) {
        log.info("Поиск абонимента по id: {}", id);
        Membership membership = membershipRepository.findById(id).orElseThrow(() -> new NotFoundByIdException("Членства с данным id ненайдено"));
        return membershipMapper.toDto(membership);
    }

    @Override
    public List<MembershipResponseDto> findByClientId(Long clientId) {
        log.info("Поиск абонимента по id клиента: {}", clientId);
        List<Membership> memberships = membershipRepository.findByClientId(clientId);
        return membershipMapper.toDtoList(memberships);
    }

    @Override
    public MembershipResponseDto findByClientIdAndActiveTrue(Long clientId) {
        log.info("Поиск активного абонемента клиента: {}", clientId);
        Membership membership = membershipRepository.findByClientIdAndIsActiveTrue(clientId).orElseThrow(() -> new NotFoundByIdException("Членства с данным id ненайдено"));
        return membershipMapper.toDto(membership);
    }

    @Override
    public List<MembershipResponseDto> findByActiveTrueAndEndDateBefore(LocalDate date) {
        log.info("Поиск истёкших абонементов до даты: {}", date);
        List<Membership> memberships = membershipRepository.findByIsActiveTrueAndEndDateBefore(date);
        return membershipMapper.toDtoList(memberships);
    }

    @Override
    public List<MembershipResponseDto> findByType(TypeOfMembership type) {
        log.info("Поиск абонементов по типу: {}", type);
        List<Membership> memberships = membershipRepository.findByType(type);
        return membershipMapper.toDtoList(memberships);
    }

    @Override
    public List<MembershipResponseDto> showAll() {
        log.info("Вывод списка всех абонементов");
        List<Membership> memberships = membershipRepository.findAll();
        return membershipMapper.toDtoList(memberships);
    }

    @Override
    public void updateInfoAboutMembership(Long membershipId, UpdateMembershipDto updateMembershipDto) {
        log.info("Обновление абонемента: id={}, {}", membershipId, updateMembershipDto);
        Membership membership = membershipRepository.findById(membershipId).orElseThrow(() -> new NotFoundByIdException("Членства с данным id ненайдено"));
        if (updateMembershipDto.getEndDate() != null) {
            membership.setEndDate(updateMembershipDto.getEndDate());
        }
        if (updateMembershipDto.getActive() != null) {
            membership.setActive(updateMembershipDto.getActive());
        }
        if (updateMembershipDto.getRemainingVisits() != null) {
            membership.setRemainingVisits(updateMembershipDto.getRemainingVisits());
        }
        membershipRepository.save(membership);
    }

    @Override
    public void deleteMembership(Long membershipId) {
        log.info("Удаление абонимента по его id: {}", membershipId);
        Membership membership = membershipRepository.findById(membershipId).orElseThrow(() -> new NotFoundByIdException("Членства с данным id ненайдено"));
        membershipRepository.delete(membership);
    }
}
