package dev.sanderk.home_media_server.component;

import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.model.Role;
import dev.sanderk.home_media_server.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserFactoryTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRoleManager userRoleManager;

    @InjectMocks
    private UserFactory userFactory;

    @Test
    void createNewUserFromDto_Success() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test_username");
        userDTO.setPassword("test_password");

        Role role = new Role();
        role.setRoleName(Role.DEFAULT_USER_ROLE);

        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encoded_password");
        when(userRoleManager.assignRole(Role.DEFAULT_USER_ROLE)).thenReturn(Set.of(role));

        // Act
        User user = userFactory.createNewUserFromDto(userDTO, Role.DEFAULT_USER_ROLE);

        // Assert
        verify(passwordEncoder).encode(userDTO.getPassword());
        verify(userRoleManager).assignRole(Role.DEFAULT_USER_ROLE);
        assertNotNull(user);
        assertEquals("test_username", user.getUsername());
        assertEquals("encoded_password", user.getPassword());
        assertTrue(user.getRoles().contains(role));
        assertEquals(1, user.getRoles().size());



    }

}
