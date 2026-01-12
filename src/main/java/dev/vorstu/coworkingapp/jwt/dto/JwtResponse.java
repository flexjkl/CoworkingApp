package dev.vorstu.coworkingapp.jwt.dto;

import dev.vorstu.coworkingapp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {

    private final String type = "Bearer";

    private String accessToken;

    private String refreshToken;

    private Long userId;

    private String username;

    private Role role;

}
