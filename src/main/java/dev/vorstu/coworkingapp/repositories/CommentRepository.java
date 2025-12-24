package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.communication.Comment;
import dev.vorstu.coworkingapp.entities.places.Space;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
