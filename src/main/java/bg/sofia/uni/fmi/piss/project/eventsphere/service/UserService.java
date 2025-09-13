package bg.sofia.uni.fmi.piss.project.eventsphere.service;

import bg.sofia.uni.fmi.piss.project.eventsphere.dto.EventSphereUserDto;
import bg.sofia.uni.fmi.piss.project.eventsphere.model.EventSphereUser;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<EventSphereUserDto> register(EventSphereUserDto userDto);

  ResponseEntity login(EventSphereUserDto userDto);

  ResponseEntity<EventSphereUserDto> getAuthUser(String username);

  ResponseEntity getAuthUserProfilePic(String username);

  ResponseEntity getAllUsers();
}
