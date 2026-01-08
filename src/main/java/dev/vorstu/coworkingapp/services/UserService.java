package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
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
import org.springframework.stereotype.Service;

//todo Переписать
@Service
@RequiredArgsConstructor
public class UserService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final OwnerRepository ownerRepository;

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
        Client client = new Client();

        client.setFirstname(userCreationDTO.getFirstname());
        client.setSecondname(userCreationDTO.getSecondname());
        client.setLastname(userCreationDTO.getLastname());
        client.setEmail(userCreationDTO.getEmail());
        client.setPhoneNumber(userCreationDTO.getPhoneNumber());
        client.setEnable(userCreationDTO.isEnable());
        client.setUsername(userCreationDTO.getUsername());
        client.setRole(Role.CLIENT);
        client.setPassword(userCreationDTO.getPassword());

        return clientRepository.save(client);
    }

    private Credentials createOwner(UserCreationDTO userCreationDTO) {
        Owner owner = new Owner();

        owner.setFirstname(userCreationDTO.getFirstname());
        owner.setSecondname(userCreationDTO.getSecondname());
        owner.setLastname(userCreationDTO.getLastname());
        owner.setEmail(userCreationDTO.getEmail());
        owner.setPhoneNumber(userCreationDTO.getPhoneNumber());
        owner.setEnable(userCreationDTO.isEnable());
        owner.setUsername(userCreationDTO.getUsername());
        owner.setRole(Role.OWNER);
        owner.setPassword(userCreationDTO.getPassword());

        return ownerRepository.save(owner);
    }

    private Credentials createAdmin(UserCreationDTO userCreationDTO) {
        Admin admin = new Admin();

        admin.setUsername(userCreationDTO.getUsername());
        admin.setPassword(userCreationDTO.getPassword());
        admin.setRole(Role.ADMIN);
        admin.setEnable(userCreationDTO.isEnable());

        return adminRepository.save(admin);
    }

}
