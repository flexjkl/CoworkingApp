package dev.vorstu.coworkingapp.exceptions.notfound;

public class PersonNotFoundException extends NotFoundException {
    public PersonNotFoundException() {
        super("PERSON NOT FOUND EXCEPTION");
    }
}
