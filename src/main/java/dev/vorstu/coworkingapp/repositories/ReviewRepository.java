package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.communication.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
            select r from Review r
            join fetch r.reviewer rev
            where (r.id = coalesce(:id, r.id))
            and (rev.id = coalesce(:reviewerId, rev.id))
            and (r.reviewedSpace.id = coalesce(:spaceId, r.reviewedSpace.id))
            and (r.rate = coalesce(:rate, r.rate))
            """)
    Page<Review> findAll(
            @Param("id") Long id,
            @Param("spaceId") Long spaceId,
            @Param("reviewerId") Long reviewerId,
            @Param("rate") Integer rate,
            Pageable pageable
    );

    @Query("""
            select count(r) > 0 from Review r
            where r.id = :id and r.reviewer.id = :reviewerId
            """)
    boolean existByIdAndReviewerId(
            @Param("id") Long id,
            @Param("reviewerId") Long authorId
    );
}
