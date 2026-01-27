package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.BookingMapper;
import dev.vorstu.coworkingapp.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private CoworkingPlaceRepository coworkingPlaceRepository;
    @Mock
    private PricePlanRepository pricePlanRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void getComments_GetFilteredPage_ReturnsFilteredPage() {

    }

    @Test
    public void getCommentById_GetCommentById_ReturnsCommentWithId() {

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
    public void isBookingOwnedByClient_OwnershipCheck_Success() {

    }

    @Test
    public void isBookingOwnedByClient_OwnershipCheck_Failed() {

    }

    @Test
    public void cleanUpExpiredBookings_ExpiredBookingsDelete_Success() {

    }
}
