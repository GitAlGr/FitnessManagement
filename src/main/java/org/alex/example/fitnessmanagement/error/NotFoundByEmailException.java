package org.alex.example.fitnessmanagement.error;

public class NotFoundByEmailException extends BusinessFitnessException {
    public NotFoundByEmailException(String message) {
        super(message);
    }
}
