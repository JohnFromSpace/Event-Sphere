package bg.sofia.uni.fmi.piss.project.eventsphere.service;

import bg.sofia.uni.fmi.piss.project.eventsphere.dto.EventSphereUserDto;
import bg.sofia.uni.fmi.piss.project.eventsphere.model.EventSphereUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserAssemblerTest {
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserAssembler assembler;

    @Test
    public void toUser_PasswordEncoded(){
        String encodedPass = "encoded-pass";
        EventSphereUserDto dto = new EventSphereUserDto();
        dto.setName("username");
        dto.setEmail("email");
        dto.setPassword("password");
        Mockito.when(encoder.encode(dto.getPassword())).thenReturn(encodedPass);

        EventSphereUser user = assembler.toUser(dto);
        Assert.assertEquals(encodedPass,user.getPassword());
    }
}
