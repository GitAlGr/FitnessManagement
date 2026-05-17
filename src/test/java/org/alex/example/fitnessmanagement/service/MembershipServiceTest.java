package org.alex.example.fitnessmanagement.service;

import org.alex.example.fitnessmanagement.dto.CreateMembershipDto;
import org.alex.example.fitnessmanagement.dto.MembershipResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateMembershipDto;
import org.alex.example.fitnessmanagement.entity.Client;
import org.alex.example.fitnessmanagement.entity.Membership;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;
import org.alex.example.fitnessmanagement.mapper.MembershipMapper;
import org.alex.example.fitnessmanagement.repository.MembershipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @Mock
    private MembershipRepository membershipRepository;

    @Mock
    private MembershipMapper membershipMapper;

    @InjectMocks
    private MembershipServiceImpl membershipService;

    @Test
    public void addMembershipTest() {
        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Alex");
        client.setLastName("Kervy");
        client.setEmail("al@mail.com");
        client.setPhone("79995554466");
        client.setDateOfBirth(LocalDate.of(1985, 2, 15));

        CreateMembershipDto createMembershipDto = new CreateMembershipDto();
        createMembershipDto.setClientId(client.getId());
        createMembershipDto.setType(TypeOfMembership.SINGLE);

        Membership membership = new Membership();
        membership.setId(1L);
        membership.setClient(client);
        membership.setType(TypeOfMembership.SINGLE);

        when(membershipMapper.toEntity(createMembershipDto)).thenReturn(membership);
        when(membershipRepository.save(membership)).thenReturn(membership);

        membershipService.add(createMembershipDto);

        verify(membershipMapper, times(1)).toEntity(createMembershipDto);
        verify(membershipRepository, times(1)).save(membership);
    }

    @Test
    public void findByIdTest() {
        long membershipId = 1L;

        Client client = new Client();
        client.setId(1L);

        Membership membership = new Membership();
        membership.setId(membershipId);
        membership.setClient(client);
        membership.setType(TypeOfMembership.SINGLE);

        MembershipResponseDto membershipResponseDto = new MembershipResponseDto();
        membershipResponseDto.setId(membershipId);
        membership.setClient(client);
        membershipResponseDto.setType(TypeOfMembership.SINGLE);

        when(membershipMapper.toDto(membership)).thenReturn(membershipResponseDto);
        when(membershipRepository.findById(membershipId)).thenReturn(Optional.of(membership));

        membershipService.findById(membershipId);

        verify(membershipMapper, times(1)).toDto(membership);
        verify(membershipRepository, times(1)).findById(membershipId);
    }

    @Test
    public void findMembershipByClientId() {
        long clientId = 1L;

        Membership membership = new Membership();
        membership.setId(1L);
        membership.setType(TypeOfMembership.SINGLE);

        Membership membership2 = new Membership();
        membership2.setId(2L);
        membership2.setType(TypeOfMembership.MONTHLY);

        List<Membership> membershipList = new ArrayList<>();
        membershipList.add(membership);
        membershipList.add(membership2);

        MembershipResponseDto membershipResponseDto = new MembershipResponseDto();
        membershipResponseDto.setId(1L);
        membershipResponseDto.setType(TypeOfMembership.SINGLE);

        MembershipResponseDto membershipResponseDto2 = new MembershipResponseDto();
        membershipResponseDto2.setId(2L);
        membershipResponseDto2.setType(TypeOfMembership.SINGLE);

        List<MembershipResponseDto> membershipResponseDtoList = new ArrayList<>();
        membershipResponseDtoList.add(membershipResponseDto);
        membershipResponseDtoList.add(membershipResponseDto2);

        when(membershipMapper.toDtoList(membershipList)).thenReturn(membershipResponseDtoList);
        when(membershipRepository.findByClientId(clientId)).thenReturn(membershipList);

        membershipService.findByClientId(clientId);

        verify(membershipMapper, times(1)).toDtoList(membershipList);
        verify(membershipRepository, times(1)).findByClientId(clientId);
    }

    @Test
    public void findByClientIdAndActiveTrueTest() {
        long clientId = 1L;

        Client client = new Client();
        client.setId(clientId);

        Membership membership = new Membership();
        membership.setId(1L);
        membership.setType(TypeOfMembership.SINGLE);
        membership.setClient(client);

        MembershipResponseDto membershipResponseDto = new MembershipResponseDto();
        membershipResponseDto.setId(1L);
        membershipResponseDto.setClientId(clientId);
        membershipResponseDto.setType(TypeOfMembership.SINGLE);

        when(membershipMapper.toDto(membership)).thenReturn(membershipResponseDto);
        when(membershipRepository.findByClientIdAndIsActiveTrue(clientId)).thenReturn(Optional.of(membership));

        membershipService.findByClientIdAndActiveTrue(clientId);

        verify(membershipMapper, times(1)).toDto(membership);
        verify(membershipRepository, times(1)).findByClientIdAndIsActiveTrue(clientId);
    }

    @Test
    public void findByActiveTrueAndEndDateBeforeTest() {
        LocalDate date = LocalDate.of(2026, 5, 5);

        Membership membership1 = new Membership();
        membership1.setId(1L);
        membership1.setType(TypeOfMembership.SINGLE);

        Membership membership2 = new Membership();
        membership2.setId(2L);
        membership2.setType(TypeOfMembership.MONTHLY);

        List<Membership> memberships = new ArrayList<>();
        memberships.add(membership1);
        memberships.add(membership2);

        MembershipResponseDto dto1 = new MembershipResponseDto();
        dto1.setId(1L);
        dto1.setType(TypeOfMembership.SINGLE);

        MembershipResponseDto dto2 = new MembershipResponseDto();
        dto2.setId(2L);
        dto2.setType(TypeOfMembership.MONTHLY);

        List<MembershipResponseDto> dtoList = new ArrayList<>();
        dtoList.add(dto1);
        dtoList.add(dto2);

        when(membershipMapper.toDtoList(memberships)).thenReturn(dtoList);
        when(membershipRepository.findByIsActiveTrueAndEndDateBefore(date)).thenReturn(memberships);

        membershipService.findByActiveTrueAndEndDateBefore(date);

        verify(membershipMapper, times(1)).toDtoList(memberships);
        verify(membershipRepository, times(1)).findByIsActiveTrueAndEndDateBefore(date);
    }

    @Test
    public void findByTypeTest() {
        TypeOfMembership type = TypeOfMembership.SINGLE;

        Membership membership1 = new Membership();
        membership1.setId(1L);
        membership1.setType(TypeOfMembership.SINGLE);

        Membership membership2 = new Membership();
        membership2.setId(2L);
        membership2.setType(TypeOfMembership.SINGLE);

        List<Membership> memberships = new ArrayList<>();
        memberships.add(membership1);
        memberships.add(membership2);

        MembershipResponseDto dto1 = new MembershipResponseDto();
        dto1.setId(1L);
        dto1.setType(TypeOfMembership.SINGLE);

        MembershipResponseDto dto2 = new MembershipResponseDto();
        dto2.setId(2L);
        dto2.setType(TypeOfMembership.SINGLE);

        List<MembershipResponseDto> dtoList = new ArrayList<>();
        dtoList.add(dto1);
        dtoList.add(dto2);

        when(membershipMapper.toDtoList(memberships)).thenReturn(dtoList);
        when(membershipRepository.findByType(type)).thenReturn(memberships);

        membershipService.findByType(type);

        verify(membershipMapper, times(1)).toDtoList(memberships);
        verify(membershipRepository, times(1)).findByType(type);
    }

    @Test
    public void findAllMembershipsTest() {
        Membership membership1 = new Membership();
        membership1.setId(1L);
        membership1.setType(TypeOfMembership.SINGLE);

        Membership membership2 = new Membership();
        membership2.setId(2L);
        membership2.setType(TypeOfMembership.MONTHLY);

        List<Membership> memberships = new ArrayList<>();
        memberships.add(membership1);
        memberships.add(membership2);

        MembershipResponseDto dto1 = new MembershipResponseDto();
        dto1.setId(1L);
        dto1.setType(TypeOfMembership.SINGLE);

        MembershipResponseDto dto2 = new MembershipResponseDto();
        dto2.setId(2L);
        dto2.setType(TypeOfMembership.MONTHLY);

        List<MembershipResponseDto> dtoList = new ArrayList<>();
        dtoList.add(dto1);
        dtoList.add(dto2);

        when(membershipMapper.toDtoList(memberships)).thenReturn(dtoList);
        when(membershipRepository.findAll()).thenReturn(memberships);

        membershipService.showAll();

        verify(membershipMapper, times(1)).toDtoList(memberships);
        verify(membershipRepository, times(1)).findAll();
    }

    @Test
    public void updateMembershipTest() {
        long membershipId = 1L;

        Membership membership = new Membership();
        membership.setId(membershipId);
        membership.setType(TypeOfMembership.SINGLE);
        membership.setActive(true);
        membership.setRemainingVisits(5);
        membership.setEndDate(LocalDate.of(2026, 5, 4));

        UpdateMembershipDto updateMembershipDto = new UpdateMembershipDto();
        updateMembershipDto.setActive(false);
        updateMembershipDto.setRemainingVisits(10);
        updateMembershipDto.setEndDate(LocalDate.of(2026, 5, 5));

        when(membershipRepository.findById(membershipId)).thenReturn(Optional.of(membership));

        membershipService.updateInfoAboutMembership(membershipId, updateMembershipDto);

        verify(membershipRepository, times(1)).findById(membershipId);
        verify(membershipRepository, times(1)).save(membership);
    }

    @Test
    public void deleteMembership() {
        long membershipId = 1L;

        Membership membership = new Membership();
        membership.setId(membershipId);

        when(membershipRepository.findById(membershipId)).thenReturn(Optional.of(membership));

        membershipService.deleteMembership(membershipId);

        verify(membershipRepository, times(1)).findById(membershipId);
        verify(membershipRepository, times(1)).delete(membership);
    }
}
