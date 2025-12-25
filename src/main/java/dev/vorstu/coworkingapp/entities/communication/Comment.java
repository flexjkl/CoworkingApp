package dev.vorstu.coworkingapp.entities.communication;

import dev.vorstu.coworkingapp.entities.places.Space;
import dev.vorstu.coworkingapp.entities.users.Person;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commented_space_id")
    private Space commentedSpace;

    private String text;

    private Long parentCommentId;

    @CreatedDate
    private Instant createdAt;
}
