package org.alex.example.fitnessmanagement.error;

public class NotFoundByNameException extends BusinessFitnessException {
    public NotFoundByNameException(String message) {
        super(message);
    }
}
