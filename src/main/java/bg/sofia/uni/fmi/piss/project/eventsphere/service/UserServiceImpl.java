package bg.sofia.uni.fmi.piss.project.eventsphere.service;

import bg.sofia.uni.fmi.piss.project.eventsphere.dto.ImageDto;
import bg.sofia.uni.fmi.piss.project.eventsphere.dto.EventSphereUserDto;
import bg.sofia.uni.fmi.piss.project.eventsphere.model.EventSphereUser;
import bg.sofia.uni.fmi.piss.project.eventsphere.repository.EventSphereUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static bg.sofia.uni.fmi.piss.project.eventsphere.SecurityConstants.USER_DIR;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EventSphereUserRepository userRepository;

    @Autowired
    private UserAssembler userAssembler;

    @Override
    public ResponseEntity<EventSphereUserDto> register(EventSphereUserDto userDto) {
        EventSphereUser existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser != null) {
          return new ResponseEntity<>(HttpStatus.CONFLICT);
        }


        EventSphereUser user = userAssembler.toUser(userDto);
        try {

            Path path = Paths.get(USER_DIR + user.getUsername());
            Files.createDirectories(path);
            System.out.println("Directory is created!");

        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userRepository.save(user);
        return new ResponseEntity<>(userAssembler.toUserDto(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity login(EventSphereUserDto userDto) {
        EventSphereUser user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
          return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EventSphereUserDto> getAuthUser(String username) {
        EventSphereUser user = userRepository.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userAssembler.toUserDto(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllUsers() {
        List<EventSphereUser> allUsers = userRepository.findAll();

        if (allUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allUsers
                .stream()
                .map(user -> userAssembler.toUserDto(user))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAuthUserProfilePic(String username) {

        ImageDto image = null;

        try {
            image = new ImageDto(USER_DIR + username + File.separator + "profile_pic.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(image , HttpStatus.OK);
    }
}

