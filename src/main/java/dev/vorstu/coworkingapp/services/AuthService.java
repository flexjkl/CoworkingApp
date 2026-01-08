package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.PersonCreationDTO;
import dev.vorstu.coworkingapp.entities.users.Credentials;
import dev.vorstu.coworkingapp.exceptions.alreadyexist.PersonAlreadyExistException;
import dev.vorstu.coworkingapp.exceptions.invalid.InvalidPasswordException;
import dev.vorstu.coworkingapp.exceptions.invalid.InvalidTokenException;
import dev.vorstu.coworkingapp.exceptions.notfound.CredentialsNotFoundException;
import dev.vorstu.coworkingapp.jwt.JwtProvider;
import dev.vorstu.coworkingapp.jwt.dto.JwtRequest;
import dev.vorstu.coworkingapp.jwt.dto.JwtResponse;
import dev.vorstu.coworkingapp.repositories.CredentialsRepository;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CredentialService credentialService;
    private final CredentialsRepository credentialsRepository;
    private final JwtProvider jwtProvider;
    private final ConcurrentHashMap<String, String> refreshStorage = new ConcurrentHashMap<>();

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final Credentials credentials = credentialsRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(CredentialsNotFoundException::new);
        if (credentials.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(credentials);
            final String refreshToken = jwtProvider.generateRefreshToken(credentials);
            refreshStorage.put(credentials.getUsername(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new InvalidPasswordException();
        }
    }

    public JwtResponse regist(@NonNull PersonCreationDTO personCreationDTO) {
        if(credentialsRepository.existsByUsername(personCreationDTO.getUsername())) {
            throw new PersonAlreadyExistException();
        }
        Credentials credentials = credentialService.createPerson(personCreationDTO);
        final String accessToken = jwtProvider.generateAccessToken(credentials);
        final String refreshToken = jwtProvider.generateRefreshToken(credentials);
        refreshStorage.put(credentials.getUsername(), refreshToken);
        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Credentials user = credentialsRepository.findByUsername(username)
                        .orElseThrow(CredentialsNotFoundException::new);
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Credentials credentials = credentialsRepository.findByUsername(username)
                        .orElseThrow(CredentialsNotFoundException::new);
                final String accessToken = jwtProvider.generateAccessToken(credentials);
                final String newRefreshToken = jwtProvider.generateRefreshToken(credentials);
                refreshStorage.put(credentials.getUsername(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new InvalidTokenException();
    }
    //todo Разкомментить после подключение SS
    /*
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
     */
}
