package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.CoworkingPlaceMapper;
import dev.vorstu.coworkingapp.repositories.CoworkingPlaceRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CoworkingPlaceServiceTest {

    @Mock
    private CoworkingPlaceRepository coworkingPlaceRepository;
    @Mock
    private SpaceRepository spaceRepository;
    @Mock
    private CoworkingPlaceMapper coworkingPlaceMapper;
    
    @InjectMocks
    private CoworkingPlaceService coworkingPlaceService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getPlaces_GetFilteredPage_ReturnsFilteredPage() {

    }

    @Test
    public void getPlaceById_GetPlaceById_ReturnsPlaceWithId() {

    }

    @Test
    public void createPlace_CreatingPlace_Success() {

    }

    @Test
    public void updatePlace_UpdatingPlace_Success() {

    }

    @Test
    public void deletePlace_DeletingPlace_Success() {

    }
}
