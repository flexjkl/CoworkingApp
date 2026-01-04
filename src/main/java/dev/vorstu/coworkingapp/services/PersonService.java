package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.PersonCreationDTO;
import dev.vorstu.coworkingapp.entities.users.Client;
import dev.vorstu.coworkingapp.entities.users.Owner;
import dev.vorstu.coworkingapp.enums.Role;
import dev.vorstu.coworkingapp.exceptions.alreadyexist.PersonAlreadyExistException;
import dev.vorstu.coworkingapp.repositories.ClientRepository;
import dev.vorstu.coworkingapp.repositories.OwnerRepository;
import dev.vorstu.coworkingapp.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//todo Переписать
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final ClientRepository clientRepository;
    private final OwnerRepository ownerRepository;

    public synchronized PersonCreationDTO createPerson(PersonCreationDTO personCreationDTO) {
        if(personRepository.existsByUsername(personCreationDTO.getUsername())) {
            throw new PersonAlreadyExistException();
        }

        if(personCreationDTO.getRole() == Role.CLIENT) {
            createClient(personCreationDTO);
        }

        else if(personCreationDTO.getRole() == Role.OWNER) {
            createOwner(personCreationDTO);
        }

        else {
            throw new IllegalStateException();
        }

        return personCreationDTO;
    }

    private void createClient(PersonCreationDTO personCreationDTO) {
        Client client = new Client();

        client.setFirstname(personCreationDTO.getFirstname());
        client.setSecondname(personCreationDTO.getSecondname());
        client.setLastname(personCreationDTO.getLastname());
        client.setEmail(personCreationDTO.getEmail());
        client.setPhoneNumber(personCreationDTO.getPhoneNumber());
        client.setEnable(personCreationDTO.isEnable());
        client.setUsername(personCreationDTO.getUsername());
        client.setRole(Role.CLIENT);
        client.setPassword(personCreationDTO.getPassword());

        clientRepository.save(client);
    }

    private void createOwner(PersonCreationDTO personCreationDTO) {
        Owner owner = new Owner();

        owner.setFirstname(personCreationDTO.getFirstname());
        owner.setSecondname(personCreationDTO.getSecondname());
        owner.setLastname(personCreationDTO.getLastname());
        owner.setEmail(personCreationDTO.getEmail());
        owner.setPhoneNumber(personCreationDTO.getPhoneNumber());
        owner.setEnable(personCreationDTO.isEnable());
        owner.setUsername(personCreationDTO.getUsername());
        owner.setRole(Role.OWNER);
        owner.setPassword(personCreationDTO.getPassword());

        ownerRepository.save(owner);
    }

}
