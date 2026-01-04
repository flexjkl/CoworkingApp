package dev.vorstu.coworkingapp.exceptions.alreadyexist;

public abstract class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String message) {
        super(message);
    }
}
