package dev.vorstu.coworkingapp;

import dev.vorstu.coworkingapp.entities.places.Space;
import dev.vorstu.coworkingapp.entities.users.Owner;
import dev.vorstu.coworkingapp.enums.Role;
import dev.vorstu.coworkingapp.repositories.OwnerRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final SpaceRepository spaceRepository;
    private final OwnerRepository ownerRepository;

    @PostConstruct
    public void init() {

        Owner owner = new Owner();
        owner.setUsername("test");
        owner.setPassword("1234");
        owner.setRole(Role.OWNER);

        ownerRepository.save(owner);

        Space space1 = new Space();
        space1.setOwner(owner);
        space1.setTitle("fasdfasdf");

        Space space2 = new Space();
        space2.setOwner(owner);
        space2.setTitle("fadsfaasdf");

        Space space3 = new Space();
        space3.setOwner(owner);
        space3.setTitle("hffghsdf");

        spaceRepository.saveAll(List.of(space1, space2, space3));

    }

}
