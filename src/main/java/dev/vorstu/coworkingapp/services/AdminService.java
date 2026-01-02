package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.*;
import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.exceptions.notfound.BookingNotFoundException;
import dev.vorstu.coworkingapp.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CommentRepository commentRepository;
    private final CoworkingPlaceRepository coworkingPlaceRepository;
    private final ReviewRepository reviewRepository;
    private final SpaceRepository spaceRepository;
    private final PersonRepository personRepository;

    private final CommentMapper commentMapper;
    private final CoworkingPlaceMapper coworkingPlaceMapper;
    private final ReviewMapper reviewMapper;
    private final SpaceMapper spaceMapper;
    private final PersonMapper personMapper;

    private final BookingService bookingService;



    public BookingOutputDTO getBookingById(Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    public Long deleteBookingById(Long bookingId) {
        return bookingService.deleteBookingById(bookingId);
    }
}
