package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpaceRepository extends JpaRepository<Space, Long>, JpaSpecificationExecutor<Space> {



}
