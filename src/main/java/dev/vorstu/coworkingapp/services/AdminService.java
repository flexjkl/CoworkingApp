package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.PersonCreationDTO;
import dev.vorstu.coworkingapp.dto.mappers.CommentMapper;
import dev.vorstu.coworkingapp.dto.mappers.CoworkingPlaceMapper;
import dev.vorstu.coworkingapp.dto.mappers.ReviewMapper;
import dev.vorstu.coworkingapp.dto.mappers.SpaceMapper;
import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.entities.users.Client;
import dev.vorstu.coworkingapp.exceptions.notfound.CommentNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.CoworkingPlaceNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.ReviewNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.SpaceNotFoundException;
import dev.vorstu.coworkingapp.repositories.CommentRepository;
import dev.vorstu.coworkingapp.repositories.CoworkingPlaceRepository;
import dev.vorstu.coworkingapp.repositories.ReviewRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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




}
