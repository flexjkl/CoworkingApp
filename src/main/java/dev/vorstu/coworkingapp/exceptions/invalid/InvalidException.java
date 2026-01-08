package dev.vorstu.coworkingapp.exceptions.invalid;

public abstract class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
