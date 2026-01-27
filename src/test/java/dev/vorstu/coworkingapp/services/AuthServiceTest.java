package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.jwt.JwtProvider;
import dev.vorstu.coworkingapp.repositories.CredentialsRepository;
import dev.vorstu.coworkingapp.repositories.RedisRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;
    @Mock
    private CredentialsRepository credentialsRepository;
    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private RedisRepository redisRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    public void login_UserSignIn_Success() {

    }

    @Test
    public void login_UserSignIn_Failed() {

    }
}
