package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.Space;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

}
