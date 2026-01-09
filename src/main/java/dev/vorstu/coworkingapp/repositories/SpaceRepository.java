package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.Space;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpaceRepository extends JpaRepository<Space, Long>, JpaSpecificationExecutor<Space> {

    @Query("""
            select s from Space s
            where (:ownerId is null or :ownerId = s.owner.id)
                  and (:title is null or lower(:title) like lower(concat('%', :title, '%')))
            """)
    Page<Space> findAll(@Param("ownerId") Long ownerId,
                        @Param("title") String titleMatcher,
                        Pageable pageable
    );

}
