package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.PersonMapper;
import dev.vorstu.coworkingapp.dto.output.PersonOutputDTO;
import dev.vorstu.coworkingapp.enums.Role;
import dev.vorstu.coworkingapp.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ReviewRepository reviewRepository;

    private final CommentRepository commentRepository;

    private final SpaceRepository spaceRepository;

    private final CoworkingPlaceRepository coworkingPlaceRepository;

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public Long deleteReview(Long id) {
        reviewRepository.deleteById(id);
        return id;
    }

    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    public Long deleteSpace(Long id) {
        spaceRepository.deleteById(id);
        return id;
    }

    public Long deleteCoworkingPlace(Long id) {
        coworkingPlaceRepository.deleteById(id);
        return id;
    }

    public Page<PersonOutputDTO> getPersons(
            Long id,
            String username,
            Role role,
            String firstname,
            String secondname,
            String lastname,
            String email,
            Pageable pageable
    ) {
        return personRepository.findAll(
                id,
                username,
                role,
                firstname,
                secondname,
                lastname,
                email,
                pageable
        ).map(personMapper::toDTO);
    }
}
