package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
import dev.vorstu.coworkingapp.dto.mappers.AdminMapper;
import dev.vorstu.coworkingapp.dto.mappers.ClientMapper;
import dev.vorstu.coworkingapp.dto.mappers.OwnerMapper;
import dev.vorstu.coworkingapp.entities.jpa.users.Admin;
import dev.vorstu.coworkingapp.entities.jpa.users.Client;
import dev.vorstu.coworkingapp.entities.jpa.users.Credentials;
import dev.vorstu.coworkingapp.entities.jpa.users.Owner;
import dev.vorstu.coworkingapp.enums.Role;
import dev.vorstu.coworkingapp.repositories.AdminRepository;
import dev.vorstu.coworkingapp.repositories.ClientRepository;
import dev.vorstu.coworkingapp.repositories.CredentialsRepository;
import dev.vorstu.coworkingapp.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CredentialsRepository credentialsRepository;
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private OwnerMapper ownerMapper;
    @Mock
    private AdminMapper adminMapper;

    @InjectMocks
    private UserService userService;

    private UserCreationDTO testUserCreationDTO;

    @BeforeEach
    public void setUp() {
        testUserCreationDTO = new UserCreationDTO();
        testUserCreationDTO.setUsername("TEST");
        testUserCreationDTO.setPassword("raw1234");
        testUserCreationDTO.setEmail("adress@email.com");
        testUserCreationDTO.setFirstname("Firstname");
        testUserCreationDTO.setSecondname("Secondname");
        testUserCreationDTO.setLastname("Lastname");
        testUserCreationDTO.setPhoneNumber("+89543422312");
    }

    @Test
    public void createUser_ClientRole_Success() {
        testUserCreationDTO.setRole(Role.CLIENT);
        String encodedPassword = "encoded1234";
        Client expectedClient = new Client();

        when(passwordEncoder.encode(testUserCreationDTO.getPassword())).thenReturn(encodedPassword);
        when(clientMapper.toEntity(testUserCreationDTO, encodedPassword)).thenReturn(expectedClient);
        when(clientRepository.save(expectedClient)).thenReturn(expectedClient);

        Credentials credentials = userService.createUser(testUserCreationDTO);

        assertNotNull(credentials);

        verify(passwordEncoder).encode("raw1234");
        verify(clientMapper).toEntity(testUserCreationDTO, encodedPassword);
        verify(clientRepository).save(expectedClient);
    }

    @Test
    public void createUser_OwnerRole_Success() {
        testUserCreationDTO.setRole(Role.OWNER);
        String encodedPassword = "encoded1234";
        Owner expectedOwner = new Owner();

        when(passwordEncoder.encode(testUserCreationDTO.getPassword())).thenReturn(encodedPassword);
        when(ownerMapper.toEntity(testUserCreationDTO, encodedPassword)).thenReturn(expectedOwner);
        when(ownerRepository.save(expectedOwner)).thenReturn(expectedOwner);

        Credentials credentials = userService.createUser(testUserCreationDTO);

        assertNotNull(credentials);

        verify(passwordEncoder).encode("raw1234");
        verify(ownerMapper).toEntity(testUserCreationDTO, encodedPassword);
        verify(ownerRepository).save(expectedOwner);
    }

    @Test
    public void createUser_AdminRole_Success() {
        testUserCreationDTO.setRole(Role.ADMIN);
        String encodedPassword = "encoded1234";
        Admin expectedAdmin = new Admin();

        when(passwordEncoder.encode(testUserCreationDTO.getPassword())).thenReturn(encodedPassword);
        when(adminMapper.toEntity(testUserCreationDTO, encodedPassword)).thenReturn(expectedAdmin);
        when(adminRepository.save(expectedAdmin)).thenReturn(expectedAdmin);

        Credentials credentials = userService.createUser(testUserCreationDTO);

        assertNotNull(credentials);

        verify(passwordEncoder).encode("raw1234");
        verify(adminMapper).toEntity(testUserCreationDTO, encodedPassword);
        verify(adminRepository).save(expectedAdmin);
    }

    @Test
    public void createUser_InvalidRole_ThrowsException() {
        testUserCreationDTO.setRole(null);

        Credentials credentials = userService.createUser(testUserCreationDTO);

        assertThrows(
                IllegalStateException.class,
                () -> userService.createUser(testUserCreationDTO)
        );

        verifyNoInteractions(
                passwordEncoder,
                credentialsRepository,
                adminRepository,
                clientRepository,
                ownerRepository,
                clientMapper,
                ownerMapper,
                adminMapper
        );
    }

    @Test
    public void deleteUser_delete_Success() {
        Long id = 5L;

        Long result = userService.deleteUser(id);

        assertEquals(id, result);

        verify(credentialsRepository).deleteById(id);
    }
}
