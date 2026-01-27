package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.CommentMapper;
import dev.vorstu.coworkingapp.repositories.CommentRepository;
import dev.vorstu.coworkingapp.repositories.PersonRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private SpaceRepository spaceRepository;
    @Mock
    private CommentMapper commentMapper;
    
    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getComments_GetFilteredPage_ReturnsFilteredPage() {

    }

    @Test
    public void createComment_CreatingComment_Success() {

    }

    @Test
    public void updateComment_UpdatingComment_Success() {

    }

    @Test
    public void deleteComment_DeletingComment_Success() {

    }

    @Test
    public void isCommentOwnedByUser_OwnershipCheck_Success() {

    }

    @Test
    public void isCommentOwnedByUser_OwnershipCheck_Failed() {

    }
    
}
