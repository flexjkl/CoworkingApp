package dev.vorstu.coworkingapp.entities.jpa.users;

import dev.vorstu.coworkingapp.entities.jpa.communication.Comment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends Credentials{

    private String firstname;

    private String secondname;

    private String lastname;

    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
