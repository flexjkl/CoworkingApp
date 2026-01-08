package dev.vorstu.coworkingapp.exceptions.notfound;

public class CredentialsNotFoundException extends NotFoundException {
    public CredentialsNotFoundException() {
        super("CREDENTIALS NOT FOUND");
    }
}
