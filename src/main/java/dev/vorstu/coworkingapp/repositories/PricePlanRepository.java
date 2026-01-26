package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.jpa.utils.PricePlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PricePlanRepository extends JpaRepository<PricePlan, Long> {

    @Query("""
            select p from PricePlan p
            where p.space.id = :spaceId
            """)
    Page<PricePlan> findAllBySpaceId(@Param("spaceId") Long spaceId,
                                     Pageable pageable);

}
