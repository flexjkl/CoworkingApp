package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {

    @Query("""
            select b from Booking b
            join fetch b.place p
            join fetch b.pricePlan pr
            join fetch p.space s
            where (b.client.id = coalesce(:clientId, b.client.id))
            and (p.id = coalesce(:placeId, p.id))
            and (pr.id = coalesce(:pricePlanId, pr.id))
            """)
    Page<Booking> findAll(@Param("clientId") Long clientId,
                          @Param("placeId") Long placeId,
                          @Param("pricePlanId") Long pricePlanId,
                          Pageable pageable
    );

    @Query("""
            select b from Booking b
            join fetch b.place
            where b.id = :id
            """)
    Optional<Booking> findByIdWithPlace(@Param("id") Long id);
}
