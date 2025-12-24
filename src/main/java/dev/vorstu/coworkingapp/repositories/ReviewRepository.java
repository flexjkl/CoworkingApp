package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.communication.Review;
import dev.vorstu.coworkingapp.entities.places.Space;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
