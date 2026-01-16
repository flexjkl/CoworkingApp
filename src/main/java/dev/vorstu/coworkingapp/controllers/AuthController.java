package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
import dev.vorstu.coworkingapp.jwt.dto.JwtRequest;
import dev.vorstu.coworkingapp.jwt.dto.JwtResponse;
import dev.vorstu.coworkingapp.jwt.dto.RefreshJwtRequest;
import dev.vorstu.coworkingapp.services.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/registration")
    public JwtResponse regist(@RequestBody UserCreationDTO userCreationDTO) {
        return authService.regist(userCreationDTO);
    }

    @PostMapping("/token")
    //todo ResponseEntity (+)
    public JwtResponse getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        return authService.getAccessToken(request.getRefreshToken());
    }

    @PostMapping("/refresh")
    @SecurityRequirement(name = "JWT")
    public JwtResponse getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        return authService.refresh(request.getRefreshToken());
    }

}
