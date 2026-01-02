package dev.vorstu.coworkingapp.exceptions.notfound;

public class BookingNotFoundException extends NotFoundException {
    public BookingNotFoundException() {
        super("BOOKING NOT FOUND EXCEPTION");
    }
}
