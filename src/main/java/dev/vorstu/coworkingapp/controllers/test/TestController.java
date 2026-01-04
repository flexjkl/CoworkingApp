package dev.vorstu.coworkingapp.controllers.test;

import dev.vorstu.coworkingapp.dto.input.PersonCreationDTO;
import dev.vorstu.coworkingapp.dto.mappers.ClientMapper;
import dev.vorstu.coworkingapp.dto.mappers.PersonMapper;
import dev.vorstu.coworkingapp.dto.output.PersonOutputDTO;
import dev.vorstu.coworkingapp.entities.users.Client;
import dev.vorstu.coworkingapp.enums.Role;
import dev.vorstu.coworkingapp.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test")
@RequiredArgsConstructor
public class TestController {

    private final ClientRepository clientRepository;
    private final PersonMapper personMapper;

    @GetMapping("/clients")
    public Page<PersonOutputDTO> getAllClients(@PageableDefault Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(personMapper::toDTO);
    }

    @PostMapping("/clients")
    public PersonCreationDTO postClient(@RequestBody PersonCreationDTO personCreationDTO) {
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
        return personCreationDTO;
    }

}
