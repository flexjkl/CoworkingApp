package dev.vorstu.coworkingapp.entities.jpa.communication;

import dev.vorstu.coworkingapp.entities.jpa.places.Space;
import dev.vorstu.coworkingapp.entities.jpa.users.Person;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
@Table(indexes =
    @Index(
        name = "parent_comment_id_index",
        columnList = "parentCommentId"
    )
)
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commented_space_id", nullable = false)
    private Space commentedSpace;

    private String text;

    private Long parentCommentId;

    @CreatedDate
    private Instant createdAt;
}
