package dev.vorstu.coworkingapp;

import dev.vorstu.coworkingapp.entities.places.CoworkingPlace;
import dev.vorstu.coworkingapp.entities.places.Space;
import dev.vorstu.coworkingapp.entities.users.Admin;
import dev.vorstu.coworkingapp.entities.users.Client;
import dev.vorstu.coworkingapp.entities.users.Owner;
import dev.vorstu.coworkingapp.enums.Role;
import dev.vorstu.coworkingapp.repositories.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final SpaceRepository spaceRepository;
    private final OwnerRepository ownerRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final ReviewRepository reviewRepository;
    private final CoworkingPlaceRepository coworkingPlaceRepository;
    private final CommentRepository commentRepository;
    private final BookingRepository bookingRepository;

    @PostConstruct
    @Transactional
    public void init() {

        Client client = new Client();
        client.setUsername("testClient1");
        client.setPassword("1234");
        client.setRole(Role.CLIENT);

        Client client1 = new Client();
        client1.setUsername("testClient2");
        client1.setPassword("1234");
        client1.setRole(Role.CLIENT);

        Client client2 = new Client();
        client2.setUsername("testClient3");
        client2.setPassword("1234");
        client2.setRole(Role.CLIENT);

        clientRepository.saveAll(List.of(client1, client2, client));



        Owner owner = new Owner();
        owner.setUsername("test");
        owner.setPassword("1234");
        owner.setRole(Role.OWNER);

        ownerRepository.save(owner);



        Admin admin = new Admin();
        admin.setUsername("test132");
        admin.setPassword("1234");
        admin.setRole(Role.ADMIN);

        adminRepository.save(admin);



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

        CoworkingPlace coworkingPlace = new CoworkingPlace();
        coworkingPlace.setSpace(space1);
        coworkingPlace.setTitle("fsdafs");
        coworkingPlace.setDescription("fasdfsdf");

        CoworkingPlace coworkingPlace1 = new CoworkingPlace();
        coworkingPlace1.setSpace(space1);
        coworkingPlace1.setTitle("fsdafs");
        coworkingPlace1.setDescription("fasdfsdf");

        CoworkingPlace coworkingPlace2 = new CoworkingPlace();
        coworkingPlace2.setSpace(space1);
        coworkingPlace2.setTitle("fsdafs");
        coworkingPlace2.setDescription("fasdfsdf");

        space1.setPlaces(List.of(coworkingPlace, coworkingPlace2, coworkingPlace1));

        CoworkingPlace coworkingPlace3 = new CoworkingPlace();
        coworkingPlace3.setSpace(space2);
        coworkingPlace3.setTitle("fsdafs");
        coworkingPlace3.setDescription("fasdfsdf");

        CoworkingPlace coworkingPlace4 = new CoworkingPlace();
        coworkingPlace4.setSpace(space2);
        coworkingPlace4.setTitle("fsdafs");
        coworkingPlace4.setDescription("fasdfsdf");

        CoworkingPlace coworkingPlace5 = new CoworkingPlace();
        coworkingPlace5.setSpace(space2);
        coworkingPlace5.setTitle("fsdafs");
        coworkingPlace5.setDescription("fasdfsdf");

        space2.setPlaces(List.of(coworkingPlace3, coworkingPlace4, coworkingPlace5));


        CoworkingPlace coworkingPlace6 = new CoworkingPlace();
        coworkingPlace6.setSpace(space3);
        coworkingPlace6.setTitle("fsdafs");
        coworkingPlace6.setDescription("fasdfsdf");

        CoworkingPlace coworkingPlace7 = new CoworkingPlace();
        coworkingPlace7.setSpace(space3);
        coworkingPlace7.setTitle("fsdafs");
        coworkingPlace7.setDescription("fasdfsdf");

        CoworkingPlace coworkingPlace8 = new CoworkingPlace();
        coworkingPlace8.setSpace(space3);
        coworkingPlace8.setTitle("fsdafs");
        coworkingPlace8.setDescription("fasdfsdf");

        space3.setPlaces(List.of(coworkingPlace6, coworkingPlace7, coworkingPlace8));

    }

}
