package org.alex.example.fitnessmanagement.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorException {
    @ExceptionHandler(BusinessFitnessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleBusinessError(BusinessFitnessException exception) {
        return new ErrorDto(400, exception.getMessage());
    }

    @ExceptionHandler(NotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundById(NotFoundByIdException exception) {
        return new ErrorDto(404, exception.getMessage());
    }

    @ExceptionHandler(NotFoundByPhoneNumberException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundByPhoneNumberException(NotFoundByPhoneNumberException exception) {
        return new ErrorDto(404, exception.getMessage());
    }

    @ExceptionHandler(NotFoundByNameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundByNameException(NotFoundByNameException exception) {
        return new ErrorDto(404, exception.getMessage());
    }

    @ExceptionHandler(NotFoundByEmailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundByEmailException(NotFoundByEmailException exception) {
        return new ErrorDto(404, exception.getMessage());
    }
}