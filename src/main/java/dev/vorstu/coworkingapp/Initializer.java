package dev.vorstu.coworkingapp;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
import dev.vorstu.coworkingapp.enums.Role;
import dev.vorstu.coworkingapp.repositories.*;
import dev.vorstu.coworkingapp.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final UserService userService;

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

        UserCreationDTO client = new UserCreationDTO();
        client.setUsername("testClient1");
        client.setPassword("1234");
        client.setFirstname("fsdfasdf");
        client.setSecondname("fdasfasd");
        client.setLastname("fasdfasf");
        client.setEmail("aagdgg");
        client.setPhoneNumber("4353553454");
        client.setRole(Role.CLIENT);

        UserCreationDTO client1 = new UserCreationDTO();
        client1.setUsername("testClient2");
        client1.setPassword("1234");
        client1.setFirstname("fsdfasdf");
        client1.setSecondname("fdasfasd");
        client1.setLastname("fasdfasf");
        client1.setEmail("aagdgg");
        client1.setPhoneNumber("4353553454");
        client1.setRole(Role.CLIENT);

        UserCreationDTO client2 = new UserCreationDTO();
        client2.setUsername("testClient3");
        client2.setPassword("1234");
        client2.setFirstname("fsdfasdf");
        client2.setSecondname("fdasfasd");
        client2.setLastname("fasdfasf");
        client2.setEmail("aagdgg");
        client2.setPhoneNumber("4353553454");
        client2.setRole(Role.CLIENT);

        userService.createUser(client2);
        userService.createUser(client1);
        userService.createUser(client);



        UserCreationDTO owner = new UserCreationDTO();
        owner.setUsername("test");
        owner.setPassword("1234");
        owner.setFirstname("fsdfasdf");
        owner.setSecondname("fdasfasd");
        owner.setLastname("fasdfasf");
        owner.setEmail("aagdgg");
        owner.setPhoneNumber("4353553454");
        owner.setRole(Role.OWNER);

        userService.createUser(owner);



        UserCreationDTO admin = new UserCreationDTO();
        admin.setUsername("test132");
        admin.setPassword("1234");
        admin.setRole(Role.ADMIN);

        userService.createUser(admin);


        /*
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

        space1.getPlaces().addAll(List.of(coworkingPlace1, coworkingPlace2, coworkingPlace));

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

        space2.getPlaces().addAll(List.of(coworkingPlace3, coworkingPlace4, coworkingPlace5));

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

        space3.getPlaces().addAll(List.of(coworkingPlace6, coworkingPlace7, coworkingPlace8));

        spaceRepository.saveAll(List.of(space1, space2, space3));
        */
    }
}
