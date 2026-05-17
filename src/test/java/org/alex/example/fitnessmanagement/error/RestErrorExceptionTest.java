package org.alex.example.fitnessmanagement.error;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestErrorExceptionTest {

    private final RestErrorException restErrorException = new RestErrorException();

    @Test
    public void handleBusinessErrorTest() {
        String message = "Что-то не так";
        BusinessFitnessException exception = new BusinessFitnessException(message);

        ErrorDto errorDto = restErrorException.handleBusinessError(exception);

        assertNotNull(errorDto);
        assertEquals(400, errorDto.getCode());
        assertEquals(message, errorDto.getMessage());
    }

    @Test
    public void handleNotFoundByIdTest() {
        String message = "Что-то не так";
        NotFoundByIdException exception = new NotFoundByIdException(message);

        ErrorDto errorDto = restErrorException.handleNotFoundById(exception);

        assertNotNull(errorDto);
        assertEquals(404, errorDto.getCode());
        assertEquals(message, errorDto.getMessage());
    }

    @Test
    public void handleNotFoundByNameTest() {
        String message = "Что-то не так";
        NotFoundByNameException exception = new NotFoundByNameException(message);

        ErrorDto errorDto = restErrorException.handleNotFoundByNameException(exception);

        assertNotNull(errorDto);
        assertEquals(404, errorDto.getCode());
        assertEquals(message, errorDto.getMessage());
    }

    @Test
    public void handleNotFoundByEmailTest() {
        String message = "Что-то не так";
        NotFoundByEmailException exception = new NotFoundByEmailException(message);

        ErrorDto errorDto = restErrorException.handleNotFoundByEmailException(exception);

        assertNotNull(errorDto);
        assertEquals(404, errorDto.getCode());
        assertEquals(message, errorDto.getMessage());
    }

    @Test
    public void handleNotFoundByPhoneTest() {
        String message = "Что-то не так";
        NotFoundByPhoneNumberException exception = new NotFoundByPhoneNumberException(message);

        ErrorDto errorDto = restErrorException.handleNotFoundByPhoneNumberException(exception);

        assertNotNull(errorDto);
        assertEquals(404, errorDto.getCode());
        assertEquals(message, errorDto.getMessage());
    }
}