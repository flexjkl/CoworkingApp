package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.*;
import dev.vorstu.coworkingapp.dto.output.*;
import dev.vorstu.coworkingapp.exceptions.notfound.*;
import dev.vorstu.coworkingapp.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private final SpaceRepository spaceRepository;
    private final SpaceMapper spaceMapper;

    private final CoworkingPlaceRepository coworkingPlaceRepository;
    private final CoworkingPlaceMapper coworkingPlaceMapper;

    private final ClientRepository clientRepository;

    private final OwnerRepository ownerRepository;

    private final PersonMapper personMapper;



    public Page<ReviewOutputDTO> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(reviewMapper::toDTO);
    }

    public ReviewOutputDTO getReview(Long id) {
        return reviewMapper.toDTO(reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new));
    }

    public Long deleteReview(Long id) {
        reviewRepository.deleteById(id);
        return id;
    }



    public Page<CommentOutputDTO> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(commentMapper::toDTO);
    }

    public CommentOutputDTO getComment(Long id) {
        return commentMapper.toDTO(commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new));
    }

    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);
        return id;
    }



    public Page<SpaceOutputDTO> getAllSpaces(Pageable pageable) {
        return spaceRepository.findAll(pageable)
                .map(spaceMapper::toDTO);
    }

    public SpaceOutputDTO getSpace(Long id) {
        return spaceMapper.toDTO(spaceRepository.findById(id)
                .orElseThrow(SpaceNotFoundException::new));
    }

    public Long deleteSpace(Long id) {
        spaceRepository.deleteById(id);
        return id;
    }



    public Page<CoworkingPlaceOutputDTO> getAllCoworkingPlaces(Pageable pageable) {
        return coworkingPlaceRepository.findAll(pageable)
                .map(coworkingPlaceMapper::toDTO);
    }

    public CoworkingPlaceOutputDTO getCoworkingPlace(Long id) {
        return coworkingPlaceMapper.toDTO(coworkingPlaceRepository.findById(id)
                .orElseThrow(CoworkingPlaceNotFoundException::new));
    }

    public Long deleteCoworkingPlace(Long id) {
        coworkingPlaceRepository.deleteById(id);
        return id;
    }



    public Page<PersonOutputDTO> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(personMapper::toDTO);
    }

    public PersonOutputDTO getClient(Long id) {
        return personMapper.toDTO(clientRepository.findById(id)
                .orElseThrow(ClientNotFoundException::new));
    }

    public Long deleteClient(Long id) {
        clientRepository.deleteById(id);
        return id;
    }



    public Page<PersonOutputDTO> getAllOwners(Pageable pageable) {
        return ownerRepository.findAll(pageable)
                .map(personMapper::toDTO);
    }

    public PersonOutputDTO getOwner(Long id) {
        return personMapper.toDTO(ownerRepository.findById(id)
                .orElseThrow(OwnerNotFoundException::new));
    }

    public Long deleteOwner(Long id) {
        ownerRepository.deleteById(id);
        return id;
    }
}
