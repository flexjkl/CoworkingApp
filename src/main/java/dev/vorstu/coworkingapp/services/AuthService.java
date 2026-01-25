package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
import dev.vorstu.coworkingapp.entities.users.Credentials;
import dev.vorstu.coworkingapp.exceptions.alreadyexist.PersonAlreadyExistException;
import dev.vorstu.coworkingapp.exceptions.invalid.InvalidPasswordException;
import dev.vorstu.coworkingapp.exceptions.invalid.InvalidTokenException;
import dev.vorstu.coworkingapp.exceptions.notfound.CredentialsNotFoundException;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.jwt.JwtProvider;
import dev.vorstu.coworkingapp.jwt.dto.JwtRequest;
import dev.vorstu.coworkingapp.jwt.dto.JwtResponse;
import dev.vorstu.coworkingapp.repositories.CredentialsRepository;
import dev.vorstu.coworkingapp.repositories.redis.JwtRedisRepository;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final CredentialsRepository credentialsRepository;

    private final JwtProvider jwtProvider;

    private final JwtRedisRepository jwtRedisRepository;

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final Credentials credentials = credentialsRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(CredentialsNotFoundException::new);
        if (passwordEncoder.matches(authRequest.getPassword(), credentials.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(credentials);
            final String refreshToken = jwtProvider.generateRefreshToken(credentials);
            jwtRedisRepository.addRefreshToken(credentials.getUsername(), refreshToken);
            return new JwtResponse(
                    accessToken,
                    refreshToken,
                    credentials.getId(),
                    credentials.getUsername(),
                    credentials.getRole()
            );
        } else {
            throw new InvalidPasswordException();
        }
    }

    public JwtResponse regist(@NonNull UserCreationDTO userCreationDTO) {
        if(credentialsRepository.existsByUsername(userCreationDTO.getUsername())) {
            throw new PersonAlreadyExistException();
        }
        Credentials credentials = userService.createUser(userCreationDTO);
        final String accessToken = jwtProvider.generateAccessToken(credentials);
        final String refreshToken = jwtProvider.generateRefreshToken(credentials);
        jwtRedisRepository.addRefreshToken(credentials.getUsername(), refreshToken);
        return new JwtResponse(
                accessToken,
                refreshToken,
                credentials.getId(),
                credentials.getUsername(),
                credentials.getRole()
        );
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = jwtRedisRepository.findRefreshTokenByUsername(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Credentials user = credentialsRepository.findByUsername(username)
                        .orElseThrow(CredentialsNotFoundException::new);
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(
                        accessToken,
                        null,
                        null,
                        null,
                        null
                );
            }
        }
        return new JwtResponse(
                null,
                null,
                null,
                null,
                null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = jwtRedisRepository.findRefreshTokenByUsername(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Credentials credentials = credentialsRepository.findByUsername(username)
                        .orElseThrow(CredentialsNotFoundException::new);
                final String accessToken = jwtProvider.generateAccessToken(credentials);
                final String newRefreshToken = jwtProvider.generateRefreshToken(credentials);
                jwtRedisRepository.addRefreshToken(credentials.getUsername(), newRefreshToken);
                return new JwtResponse(
                        accessToken,
                        newRefreshToken,
                        credentials.getId(),
                        credentials.getUsername(),
                        credentials.getRole()
                );
            }
        }
        throw new InvalidTokenException();
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
