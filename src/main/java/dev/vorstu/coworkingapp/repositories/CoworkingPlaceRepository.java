package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.CoworkingPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoworkingPlaceRepository extends JpaRepository<CoworkingPlace, Long> {

    @Query("""
            select c from CoworkingPlace c
            join fetch c.space s
            where s.id = :spaceId
            """)
    Page<CoworkingPlace> findAllBySpaceId(@Param("spaceId") Long spaceId, Pageable pageable);

}
