package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.CoworkingPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoworkingPlaceRepository extends JpaRepository<CoworkingPlace, Long>, JpaSpecificationExecutor<CoworkingPlace> {

    @Query("""
            select c from CoworkingPlace c
            join fetch c.space s
            where s.id = coalesce(:spaceId, s.id))
            and (lower(c.title) like lower(concat('%', coalesce(:titleMatcher, ''), '%')))
            and (c.isFree = coalesce(:free, c.isFree))
            """)
    Page<CoworkingPlace> findAll(@Param("spaceId") Long spaceId,
                                 @Param("titleMatcher") String titleMatcher,
                                 @Param("free") Boolean free,
                                 Pageable pageable);

}
