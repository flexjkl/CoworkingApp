package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
import dev.vorstu.coworkingapp.dto.mappers.SpaceMapper;
import dev.vorstu.coworkingapp.dto.output.PersonOutputDTO;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.entities.jpa.places.Space;
import dev.vorstu.coworkingapp.repositories.OwnerRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpaceServiceTest {

    @Mock
    private SpaceRepository spaceRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private SpaceMapper spaceMapper;

    @InjectMocks
    private SpaceService spaceService;

    private Page<SlimSpaceOutputDTO> expectedFilteredPage;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getSpaces_GetFilteredPage_ReturnsFilteredPage() {

    }

    @Test
    public void getSpaceById_GetSpaceById_ReturnsSpaceWithId() {

    }

    @Test
    public void createSpace_CreatingSpace_Success() {

    }

    @Test
    public void updateSpace_UpdatingSpace_Success() {

    }

    @Test
    public void deleteSpace_DeletingSpace_Success() {

    }

    @Test
    public void isSpaceOwnedByOwner_OwnershipCheck_Success() {

    }

    @Test
    public void isSpaceOwnedByOwner_OwnershipCheck_Failed() {

    }
}
