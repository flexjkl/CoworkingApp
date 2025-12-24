package dev.vorstu.coworkingapp.entities.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends Credentials{

    private String firstname;

    private String secondname;

    private String lastname;

    @Column(unique = true)
    private String email;

    private String phoneNumber;
}
