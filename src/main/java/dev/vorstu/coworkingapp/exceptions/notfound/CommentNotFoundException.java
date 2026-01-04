package dev.vorstu.coworkingapp.exceptions.notfound;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException() {
        super("COMMENT NOT FOUND");
    }
}
