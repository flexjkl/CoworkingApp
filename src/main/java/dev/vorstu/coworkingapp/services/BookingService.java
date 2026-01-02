package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.BookingMapper;
import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.exceptions.notfound.BookingNotFoundException;
import dev.vorstu.coworkingapp.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;

    public BookingOutputDTO getBookingById(Long bookingId) {
        return bookingMapper.toDTO(bookingRepository.findById(bookingId).orElseThrow(BookingNotFoundException::new));
    }

    public Long deleteBookingById(Long bookingId) {
        bookingRepository.deleteById(bookingId);
        return bookingId;
    }
}
