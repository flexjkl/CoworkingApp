package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.communication.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    @Query("""
            select c from Comment c
            join fetch c.author a
            where (:id is null or :id = c.id)
            and (:authorId is null or :authorId = a.id)
            and (:spaceId is null or :spaceId = c.commentedSpace.id)
            and (:parentId is null or :parentId = c.parentCommentId)
            """)
    Page<Comment> findAll(@Param("id") Long id,
                          @Param("authorId") Long authorId,
                          @Param("spaceId") Long commentedSpaceId,
                          @Param("parentId") Long parentId,
                          Pageable pageable
    );

}
