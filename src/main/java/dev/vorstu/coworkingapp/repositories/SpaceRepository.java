package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.Space;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.util.Optional;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    @Query("""
            select s from Space s
            where (s.owner.id = coalesce(:ownerId, s.owner.id))
            and (lower(s.title) like lower(concat('%', coalesce(:title, ''), '%')))
            """)
    Page<Space> findAll(@Param("ownerId") Long ownerId,
                        @Param("title") String titleMatcher,
                        Pageable pageable
    );

    @Query("""
            select s from Space s
            join fetch s.owner
            where s.id = :id
            """)
    Optional<Space> findByIdWithOwner(@Param("id") Long id);

    @Query("""
            select count(s) > 0 from Space s
            where s.id = :id and s.owner.id = :ownerId
            """)
    boolean existByIdAndOwnerId(@Param("id") Long id,
                                @Param("ownerId") Long ownerId
    );

    @Modifying
    @Transactional
    @Query("""
            update Space s
            set s.rating = (
                select coalesce(avg(r.rate), 0.0) from Review r
                where r.reviewedSpace.id = :id
            )
            where s.id = :id
            """)
    int recalculateSpaceRatingById(@Param("id") Long id);
}
