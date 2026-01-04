package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.communication.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
            select r from Review r
            join fetch r.reviewer
            where r.reviewedSpace.id = :spaceId
            """)
    Page<Review> findAllBySpaceId(@Param("spaceId") Long spaceId, Pageable pageable);

    @Query("""
            select r from Review r
            join fetch r.reviewer
            where r.id in :ids
            """)
    Page<Review> findAllById(@Param("ids") List<Long> ids, Pageable pageable);
}
