package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
