package org.alex.example.fitnessmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alex.example.fitnessmanagement.dto.CreateMembershipDto;
import org.alex.example.fitnessmanagement.dto.MembershipResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateMembershipDto;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;
import org.alex.example.fitnessmanagement.service.MembershipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MembershipController.class)
public class MembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MembershipService membershipService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addMembershipTest() throws Exception {
        long clientId = 1L;

        CreateMembershipDto createMembershipDto = new CreateMembershipDto();
        createMembershipDto.setClientId(clientId);
        createMembershipDto.setType(TypeOfMembership.MONTHLY);

        mockMvc.perform(post("/memberships/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createMembershipDto)))
                .andExpect(status().isOk());

        verify(membershipService, times(1)).add(any(CreateMembershipDto.class));
    }

    @Test
    public void findMembershipByIdTest() throws Exception {
        long membershipId = 1L;

        MembershipResponseDto membershipResponseDto = new MembershipResponseDto();
        membershipResponseDto.setId(membershipId);

        when(membershipService.findById(membershipId)).thenReturn(membershipResponseDto);

        mockMvc.perform(get("/memberships/by-membershipId/{membershipId}", membershipId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(membershipId));

        verify(membershipService, times(1)).findById(membershipId);
    }

    @Test
    public void findByClientIdTest() throws Exception {
        long clientId = 1L;

        MembershipResponseDto membershipResponseDto = new MembershipResponseDto();
        membershipResponseDto.setId(1L);
        membershipResponseDto.setClientId(clientId);

        MembershipResponseDto membershipResponseDto2 = new MembershipResponseDto();
        membershipResponseDto2.setId(2L);
        membershipResponseDto2.setClientId(clientId);

        List<MembershipResponseDto> membershipResponseDtoList = new ArrayList<>();
        membershipResponseDtoList.add(membershipResponseDto);
        membershipResponseDtoList.add(membershipResponseDto2);

        when(membershipService.findByClientId(clientId)).thenReturn(membershipResponseDtoList);

        mockMvc.perform(get("/memberships/by-client/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(membershipService, times(1)).findByClientId(clientId);
    }

    @Test
    public void findMembershipByTypeTest() throws Exception {
        TypeOfMembership type = TypeOfMembership.MONTHLY;

        MembershipResponseDto membershipResponseDto = new MembershipResponseDto();
        membershipResponseDto.setId(1L);
        membershipResponseDto.setType(type);

        MembershipResponseDto membershipResponseDto2 = new MembershipResponseDto();
        membershipResponseDto2.setId(2L);
        membershipResponseDto2.setType(type);

        List<MembershipResponseDto> membershipResponseDtoList = new ArrayList<>();
        membershipResponseDtoList.add(membershipResponseDto);
        membershipResponseDtoList.add(membershipResponseDto2);

        when(membershipService.findByType(type)).thenReturn(membershipResponseDtoList);

        mockMvc.perform(get("/memberships/by-type/{type}", type))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(membershipService, times(1)).findByType(type);
    }

    @Test
    public void findAllTest() throws Exception {
        MembershipResponseDto membershipResponseDto = new MembershipResponseDto();
        membershipResponseDto.setId(1L);

        MembershipResponseDto membershipResponseDto2 = new MembershipResponseDto();
        membershipResponseDto2.setId(2L);

        List<MembershipResponseDto> membershipResponseDtoList = new ArrayList<>();
        membershipResponseDtoList.add(membershipResponseDto);
        membershipResponseDtoList.add(membershipResponseDto2);

        when(membershipService.showAll()).thenReturn(membershipResponseDtoList);

        mockMvc.perform(get("/memberships/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].id").value(2L));

        verify(membershipService, times(1)).showAll();
    }

    @Test
    public void updateMembershipTest() throws Exception {
        long membershipId = 1L;

        UpdateMembershipDto updateMembershipDto = new UpdateMembershipDto();
        updateMembershipDto.setEndDate(LocalDate.of(2026, 5, 20));
        updateMembershipDto.setActive(false);
        updateMembershipDto.setRemainingVisits(5);

        mockMvc.perform(put("/memberships/put/{membershipId}", membershipId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateMembershipDto)))
                .andExpect(status().isOk());

        verify(membershipService, times(1)).updateInfoAboutMembership(eq(membershipId), any(UpdateMembershipDto.class));
    }

    @Test
    public void deleteMembershipTest() throws Exception {
        long membershipId = 1L;

        mockMvc.perform(delete("/memberships/delete/{membershipId}", membershipId))
                .andExpect(status().isOk());

        verify(membershipService, times(1)).deleteMembership(membershipId);
    }
}
