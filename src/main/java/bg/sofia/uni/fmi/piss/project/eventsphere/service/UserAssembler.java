package bg.sofia.uni.fmi.piss.project.eventsphere.service;

import bg.sofia.uni.fmi.piss.project.eventsphere.dto.EventSphereUserDto;
import bg.sofia.uni.fmi.piss.project.eventsphere.model.EventSphereUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

  @Autowired
  private PasswordEncoder passwordEncoder;

  EventSphereUser toUser(EventSphereUserDto userDto) {
    return new EventSphereUser(userDto.getUsername(),
            userDto.getEmail(),
            passwordEncoder.encode(userDto.getPassword()));
  }

  EventSphereUserDto toUserDto(EventSphereUser user) {
    EventSphereUserDto userDto = new EventSphereUserDto();
    userDto.setUserId(user.getId());
    userDto.setName(user.getUsername());
    userDto.setEmail(user.getEmail());
    return userDto;
  }
}

