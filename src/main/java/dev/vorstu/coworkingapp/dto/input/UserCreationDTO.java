package dev.vorstu.coworkingapp.dto.input;

import dev.vorstu.coworkingapp.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDTO {

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
