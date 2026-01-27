package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.PricePlanMapper;
import dev.vorstu.coworkingapp.repositories.PricePlanRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PricePlanServiceTest {

    @Mock
    private PricePlanRepository pricePlanRepository;
    @Mock
    private SpaceRepository spaceRepository;
    @Mock
    private PricePlanMapper pricePlanMapper;
    
    @InjectMocks
    private PricePlanService pricePlanService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getPricePlansBySpaceId_GetFilteredPage_ReturnsFilteredPage() {

    }


    @Test
    public void createPricePlan_CreatingPricePlan_Success() {

    }

    @Test
    public void updatePricePlan_UpdatingPricePlan_Success() {

    }

    @Test
    public void deletePricePlan_DeletingPricePlan_Success() {

    }
}
