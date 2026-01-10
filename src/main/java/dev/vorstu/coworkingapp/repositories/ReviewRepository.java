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
            select r from Review r
            join fetch r.reviewer rev
            where (:id is null or r.id = :id)
            and (:reviewerId is null or rev.id = :reviewerId)
            and (:spaceId is null or r.reviewedSpace.id = :spaceId)
            and (:rate is null or r.rate = :rate)
            """)
    Page<Review> findAll(
            @Param("id") Long id,
            @Param("spaceId") Long spaceId,
            @Param("reviewerId") Long reviewerId,
            @Param("rate") Integer rate,
            Pageable pageable
    );
}
