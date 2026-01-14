package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.BookingCreationDTO;
import dev.vorstu.coworkingapp.dto.input.ChatCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.BookingUpdateDTO;
import dev.vorstu.coworkingapp.dto.mappers.BookingMapper;
import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import dev.vorstu.coworkingapp.entities.communication.Chat;
import dev.vorstu.coworkingapp.entities.places.Booking;
import dev.vorstu.coworkingapp.entities.places.CoworkingPlace;
import dev.vorstu.coworkingapp.exceptions.notfound.BookingNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.CoworkingPlaceNotFoundException;
import dev.vorstu.coworkingapp.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final CoworkingPlaceRepository coworkingPlaceRepository;
    private final PricePlanRepository pricePlanRepository;
    private final OwnerRepository ownerRepository;

    private final BookingMapper bookingMapper;

    public Page<SlimBookingOutputDTO> getBookings(
            Long clientId,
            Long placeId,
            Long pricePlanId,
            Pageable pageable
    ) {
        return bookingRepository.findAll(clientId, placeId, pricePlanId, pageable)
                .map(bookingMapper::toSlimDTO);
    }

    public BookingOutputDTO getBooking(Long id) {
        return bookingMapper.toDTO(bookingRepository.findByIdWithPlace(id)
                .orElseThrow(BookingNotFoundException::new));
    }

    @Transactional
    public BookingCreationDTO createBooking(Long clientId, BookingCreationDTO bookingCreationDTO) {

        Booking booking = new Booking();

        booking.setAdditions(bookingCreationDTO.getAdditions());
        booking.setClient(clientRepository.getReferenceById(clientId));

        CoworkingPlace coworkingPlace = coworkingPlaceRepository.findById(bookingCreationDTO.getPlaceId())
                        .orElseThrow(CoworkingPlaceNotFoundException::new);
        booking.setPlace(coworkingPlace);

        booking.setPricePlan(pricePlanRepository.getReferenceById(bookingCreationDTO.getPricePlanId()));

        ChatCreationDTO chatCreationDTO = bookingCreationDTO.getChatCreationDTO();

        Chat chat = new Chat();

        chat.setOwner(ownerRepository.getReferenceById(chatCreationDTO.getOwnerId()));
        chat.setClient(clientRepository.getReferenceById(clientId));

        booking.setChat(chat);

        bookingRepository.save(booking);

        return bookingCreationDTO;
    }

    @Transactional
    public BookingUpdateDTO updateBooking(Long id, BookingUpdateDTO bookingUpdateDTO) {
        Booking booking = bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);

        booking.setAdditions(bookingUpdateDTO.getAdditions());

        return bookingUpdateDTO;
    }

    @Transactional
    public Long deleteBooking(Long id) {
        CoworkingPlace coworkingPlace = coworkingPlaceRepository.findByBookingId(id)
                .orElseThrow(CoworkingPlaceNotFoundException::new);

        coworkingPlace.release();

        bookingRepository.deleteById(id);

        return id;
    }

    public boolean isBookingOwnedByClient(Long id, Long clientId) {
        return bookingRepository.existByIdAndClientId(id, clientId);
    }
}
