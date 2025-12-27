package dev.vorstu.coworkingapp.dto.input;

import dev.vorstu.coworkingapp.enums.Role;

public class PersonCreationDTO {

    private String username;

    private String password;

    private Role role;

    boolean enable = true;

    private String firstname;

    private String secondname;

    private String lastname;

    private String email;

    private String phoneNumber;
}
