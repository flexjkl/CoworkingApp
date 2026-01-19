package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
import dev.vorstu.coworkingapp.dto.mappers.AdminMapper;
import dev.vorstu.coworkingapp.dto.mappers.ClientMapper;
import dev.vorstu.coworkingapp.dto.mappers.OwnerMapper;
import dev.vorstu.coworkingapp.entities.users.Admin;
import dev.vorstu.coworkingapp.entities.users.Client;
import dev.vorstu.coworkingapp.entities.users.Credentials;
import dev.vorstu.coworkingapp.entities.users.Owner;
import dev.vorstu.coworkingapp.enums.Role;
import dev.vorstu.coworkingapp.repositories.AdminRepository;
import dev.vorstu.coworkingapp.repositories.ClientRepository;
import dev.vorstu.coworkingapp.repositories.CredentialsRepository;
import dev.vorstu.coworkingapp.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final CredentialsRepository credentialsRepository;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final OwnerRepository ownerRepository;

    private final ClientMapper clientMapper;
    private final OwnerMapper ownerMapper;
    private final AdminMapper adminMapper;

    public synchronized Credentials createUser(UserCreationDTO userCreationDTO) {

        if(userCreationDTO.getRole() == Role.CLIENT) {
            return createClient(userCreationDTO);
        }

        if(userCreationDTO.getRole() == Role.OWNER) {
            return createOwner(userCreationDTO);
        }

        if(userCreationDTO.getRole() == Role.ADMIN) {
            return createAdmin(userCreationDTO);
        }

        throw new IllegalStateException();
    }

    private Credentials createClient(UserCreationDTO userCreationDTO) {
        return clientRepository.save(
                clientMapper.toEntity(
                        userCreationDTO,
                        passwordEncoder.encode(userCreationDTO.getPassword())
                )
        );
    }

    private Credentials createOwner(UserCreationDTO userCreationDTO) {
        return ownerRepository.save(
                ownerMapper.toEntity(
                        userCreationDTO,
                        passwordEncoder.encode(userCreationDTO.getPassword())
                )
        );
    }

    private Credentials createAdmin(UserCreationDTO userCreationDTO) {
        return adminRepository.save(
                adminMapper.toEntity(
                        userCreationDTO,
                        passwordEncoder.encode(userCreationDTO.getPassword())
                )
        );
    }

    public Long deleteUser(Long id) {
        credentialsRepository.deleteById(id);
        return id;
    }
}
