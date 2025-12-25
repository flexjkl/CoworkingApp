package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.CoworkingPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoworkingPlaceRepository extends JpaRepository<CoworkingPlace, Long> {
}
