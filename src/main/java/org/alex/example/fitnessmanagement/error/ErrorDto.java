package org.alex.example.fitnessmanagement.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    @Schema(description = "Код ошибки", example = "400")
    private int code;

    @Schema(description = "Описание ошибки", example = "Неверно указан id автора")
    private String message;

}
