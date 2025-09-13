package bg.sofia.uni.fmi.piss.project.eventsphere.service;

import bg.sofia.uni.fmi.piss.project.eventsphere.dto.EventSphereUserDto;
import bg.sofia.uni.fmi.piss.project.eventsphere.model.EventSphereUser;
import bg.sofia.uni.fmi.piss.project.eventsphere.repository.EventSphereUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private EventSphereUserRepository userRepository;

    @Mock
    private UserAssembler userAssembler;

    @InjectMocks
    private UserServiceImpl userService;

    private EventSphereUserDto dto;

    private EventSphereUser user;

    @Before
    public void setUp(){
        dto = new EventSphereUserDto();
        dto.setName("username");

        user = new EventSphereUser();
        user.setUsername("username");
    }

    @Test
    public void register_AlreadyExistingUser(){
        Mockito.when(userRepository.findByUsername(dto.getUsername())).
                thenReturn(new EventSphereUser());
        ResponseEntity<EventSphereUserDto> result = userService.register(dto);
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void register_UserCreatedSuccessfully(){
        Mockito.when(userRepository.findByUsername(dto.getUsername())).
                thenReturn(null);
        Mockito.when(userAssembler.toUser(dto)).thenReturn(user);
        Mockito.when(userAssembler.toUserDto(user)).thenReturn(dto);

        ResponseEntity<EventSphereUserDto> result = userService.register(dto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(dto,result.getBody());
    }

    @Test
    public void login_UserNotFound(){
        ResponseEntity result = userService.login(dto);
        assertEquals(HttpStatus.BAD_REQUEST,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void login_UserFound(){
        Mockito.when(userRepository.findByUsername(dto.getUsername())).
                thenReturn(user);
        ResponseEntity<EventSphereUserDto> result = userService.login(dto);
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getAuthUser_UserNotFound(){
        ResponseEntity<EventSphereUserDto> result = userService.getAuthUser("username");
        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
    }

    @Test
    public void getAuthUser_UserFound(){
        Mockito.when(userRepository.findByUsername(user.getUsername())).
                thenReturn(user);
        Mockito.when(userAssembler.toUserDto(user)).thenReturn(dto);

        ResponseEntity<EventSphereUserDto> result = userService.getAuthUser(user.getUsername());
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(dto,result.getBody());
    }
}
