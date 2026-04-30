package org.alex.example.fitnessmanagement.error;

public class NotFoundByPhoneNumberException extends BusinessFitnessException {
    public NotFoundByPhoneNumberException(String message) {
        super(message);
    }
}
