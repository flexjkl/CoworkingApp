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
            where (c.id = coalesce(:id, c.id))
            and (a.id = coalesce(:authorId, a.id))
            and (c.commentedSpace.id = coalesce(:spaceId, c.commentedSpace.id))
            and (c.parentCommentId = coalesce(:parentId, c.parentCommentId))
            """)
    Page<Comment> findAll(@Param("id") Long id,
                          @Param("authorId") Long authorId,
                          @Param("spaceId") Long commentedSpaceId,
                          @Param("parentId") Long parentId,
                          Pageable pageable
    );

    @Query("""
            select count(c) > 0 from Comment c
            where c.id = :id and c.author.id = :authorId
            """)
    boolean existByIdAndAuthorId(
            @Param("id") Long id,
            @Param("authorId") Long authorId
    );
}
