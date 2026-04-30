package org.alex.example.fitnessmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alex.example.fitnessmanagement.dto.CreateMembershipDto;
import org.alex.example.fitnessmanagement.dto.MembershipResponseDto;
import org.alex.example.fitnessmanagement.dto.UpdateMembershipDto;
import org.alex.example.fitnessmanagement.entity.TypeOfMembership;
import org.alex.example.fitnessmanagement.service.MembershipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberships")
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping("/add")
    @Operation(summary = "Добавление абонимента", description = "Регистрация абонимента в базе фитнес клуба")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Абонимент успешно добавлен"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void addMembership(@Valid @RequestBody CreateMembershipDto createMembershipDto) {
        membershipService.add(createMembershipDto);
    }

    @GetMapping("/{membershipId}")
    @Operation(summary = "Поиск абонимента", description = "Поиск абонимента в базе по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Абонимент успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public MembershipResponseDto findMembershipById(@Valid @PathVariable Long membershipId) {
        return membershipService.findById(membershipId);
    }

    @GetMapping("/by-client/{clientId}")
    @Operation(summary = "Поиск абонимента", description = "Поиск абонимента в базе по id клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Абонимент успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<MembershipResponseDto> findByClientId(@PathVariable Long clientId) {
        return membershipService.findByClientId(clientId);
    }

    @GetMapping("/by-type/{type}")
    @Operation(summary = "Поиск абонимента", description = "Поиск абонимента в базе по типу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Абонимент успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<MembershipResponseDto> findMembershipByType(@Valid @PathVariable TypeOfMembership type) {
        return membershipService.findByType(type);
    }

    @GetMapping("/all")
    @Operation(summary = "Список всех абонементов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Абонементы успешно найдены"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<MembershipResponseDto> findAll() {
        return membershipService.showAll();
    }

    @PutMapping("/put/{membershipId}")
    @Operation(summary = "Изменение данных", description = "Изменение данных абонимента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные абонимента успешно изменены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void updateMembership(@Valid @PathVariable Long membershipId, @RequestBody UpdateMembershipDto updateMembershipDto) {
        membershipService.updateInfoAboutMembership(membershipId, updateMembershipDto);
    }

    @DeleteMapping("/delete/{membershipId}")
    @Operation(summary = "Удаление", description = "Удаление абонимента из базы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Абонимент успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void deleteMembership(@Valid @PathVariable Long membershipId) {
        membershipService.deleteMembership(membershipId);
    }
}
