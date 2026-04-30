package org.alex.example.fitnessmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alex.example.fitnessmanagement.dto.ClientResponseDto;
import org.alex.example.fitnessmanagement.dto.CreateClientDto;
import org.alex.example.fitnessmanagement.dto.UpdateClientDto;
import org.alex.example.fitnessmanagement.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/add")
    @Operation(summary = "Добавление клиента", description = "Регистрация клиента в базе фитнес клуба")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент успешно добавлен"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void addClient(@Valid @RequestBody CreateClientDto createClientDto) {
        clientService.add(createClientDto);
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Поиск клиента", description = "Поиск клиента в базе по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public ClientResponseDto findClientById(@Valid @PathVariable Long clientId) {
        return clientService.findClientById(clientId);
    }

    @GetMapping("/by-firstName/{firstName}")
    @Operation(summary = "Поиск клиента", description = "Поиск клиента в базе по имени")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public ClientResponseDto findClientByFirstName(@Valid @PathVariable String firstName) {
        return clientService.findByFirstName(firstName);
    }

    @GetMapping("/by-lastName/{lastName}")
    @Operation(summary = "Поиск клиента", description = "Поиск клиента в базе по фамилии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public ClientResponseDto findClientByLastName(@Valid @PathVariable String lastName) {
        return clientService.findByLastName(lastName);
    }

    @GetMapping("/by-email/{email}")
    @Operation(summary = "Поиск клиента", description = "Поиск клиента в базе по email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public ClientResponseDto findClientByEmail(@Valid @PathVariable String email) {
        return clientService.findByEmail(email);
    }

    @GetMapping("/by-phoneNumber/{phone}")
    @Operation(summary = "Поиск клиента", description = "Поиск клиента в базе по номеру телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент успешно найден"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public ClientResponseDto findClientByPhone(@Valid @PathVariable String phone) {
        return clientService.findByPhone(phone);
    }

    @GetMapping("/activeClients")
    @Operation(summary = "Поиск клиентов", description = "Поиск активных клиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиенты успешно найдены"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<ClientResponseDto> findActiveClients() {
        return clientService.findByActiveTrue();
    }


    @GetMapping("/unactiveClients")
    @Operation(summary = "Поиск клиентов", description = "Поиск неактивных клиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиенты успешно найдены"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<ClientResponseDto> findUnactiveClients() {
        return clientService.findByActiveFalse();
    }


    @GetMapping("/allClients")
    @Operation(summary = "Поиск клиентов", description = "Поиск всех клиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиенты успешно найдены"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public List<ClientResponseDto> findAllClients() {
        return clientService.showAllClients();
    }

    @PutMapping("/put/{clientId}")
    @Operation(summary = "Изменение данных", description = "Изменение данных клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные клиента успешно изменены"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void updateClient(@Valid @PathVariable Long clientId, @RequestBody UpdateClientDto updateClientDto) {
        clientService.updateInfoAboutClient(clientId, updateClientDto);
    }

    @DeleteMapping("/delete/{clientId}")
    @Operation(summary = "Удаление", description = "Удаление клиента из базы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера, попробуйте позже")
    })
    public void deleteClient(@Valid @PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }
}
