package dev.vorstu.coworkingapp.exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("ACCESS DENIED EXCEPTION");
    }
}
