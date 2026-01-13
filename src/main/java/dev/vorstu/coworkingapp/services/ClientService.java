package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.CommentCreationDTO;
import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CommentUpdateDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.entities.communication.Comment;
import dev.vorstu.coworkingapp.exceptions.illegalaccess.IllegalAccessException;
import dev.vorstu.coworkingapp.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ReviewsService reviewsService;
    private final CommentService commentService;
    private final BookingService bookingService;


}
