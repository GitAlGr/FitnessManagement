package org.alex.example.fitnessmanagement.error;

public class NotFoundByIdException extends BusinessFitnessException {
    public NotFoundByIdException(String message) {
        super(message);
    }
}
