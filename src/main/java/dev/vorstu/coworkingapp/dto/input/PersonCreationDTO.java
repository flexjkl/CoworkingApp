package dev.vorstu.coworkingapp.dto.input;

import dev.vorstu.coworkingapp.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
