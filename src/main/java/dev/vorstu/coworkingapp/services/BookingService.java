package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.BookingMapper;
import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import dev.vorstu.coworkingapp.entities.places.Booking;
import dev.vorstu.coworkingapp.exceptions.notfound.BookingNotFoundException;
import dev.vorstu.coworkingapp.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.LongSupplier;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;

    public Long deleteBooking(Long id) {
        bookingRepository.deleteById(id);
        return id;
    }
}
