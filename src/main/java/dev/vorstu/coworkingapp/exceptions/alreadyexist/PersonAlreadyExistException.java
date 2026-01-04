package dev.vorstu.coworkingapp.exceptions.alreadyexist;

public class PersonAlreadyExistException extends AlreadyExistException {
    public PersonAlreadyExistException() {
        super("PERSON ALREADY EXIST");
    }
}
