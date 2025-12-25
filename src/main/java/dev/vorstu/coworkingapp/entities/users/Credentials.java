package dev.vorstu.coworkingapp.entities.users;

import dev.vorstu.coworkingapp.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    String username;

    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    boolean enable;

}
