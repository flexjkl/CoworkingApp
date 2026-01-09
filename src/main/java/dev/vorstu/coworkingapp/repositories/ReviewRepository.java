package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.communication.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    @Query("""
            select r from Review r
            join fetch r.reviewer
            where r.reviewedSpace.id = :spaceId
            """)
    Page<Review> findAllBySpaceId(@Param("spaceId") Long spaceId, Pageable pageable);

    @Query("""
            select r form Review r
            join fetch r.reviewer rev
            where (:id is null or :id = r.id)
            and (:reviewerId is null or :reviewerId = rev.id)
            and (:spaceId is null or :spaceId = r.reviewedSpace.id)
            and (:rate is null or :rate = r.rate)
            """)
    Page<Review> findAll(
            @Param("id") Long id,
            @Param("spaceId") Long spaceId,
            @Param("reviewerId") Long reviewerId,
            @Param("rate") Integer rate,
            Pageable pageable
    );
}
