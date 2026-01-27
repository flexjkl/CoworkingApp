package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.ReviewMapper;
import dev.vorstu.coworkingapp.repositories.ClientRepository;
import dev.vorstu.coworkingapp.repositories.ReviewRepository;
import dev.vorstu.coworkingapp.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewsServiceTest {

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ReviewRepository ReviewRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ReviewMapper reviewMapper;
    
    @InjectMocks
    private ReviewsService reviewsService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getReviews_GetFilteredPage_ReturnsFilteredPage() {

    }

    @Test
    public void createReview_CreatingReview_Success() {

    }

    @Test
    public void updateReview_UpdatingReview_Success() {

    }

    @Test
    public void deleteReview_DeletingReview_Success() {

    }

    @Test
    public void isReviewOwnedByUser_OwnershipCheck_Success() {

    }

    @Test
    public void isReviewOwnedByUser_OwnershipCheck_Failed() {

    }
}
