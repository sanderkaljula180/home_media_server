package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.component.UserFactory;
import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.exception.UserAlreadyExistsException;
import dev.sanderk.home_media_server.model.Role;
import dev.sanderk.home_media_server.model.User;
import dev.sanderk.home_media_server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserFactory userFactory;

    @InjectMocks
    private UserServiceImpl userService;

   @Test
   void whenRegisteringNewDefaultUser_Success() {

       // Arrange
       UserDTO userDTO = new UserDTO();
       userDTO.setUsername("john_doe");
       userDTO.setPassword("password1");

       User user = new User();
       user.setUsername("john_doe");
       user.setPassword("password1");

       Role role = new Role();
       role.setRoleName(Role.DEFAULT_USER_ROLE);
       Set<Role> roles = new HashSet<>();
       roles.add(role);
       user.setRoles(roles);

       when(userRepository.existsByUsername(userDTO.getUsername())).thenReturn(false);
       when(userFactory.createNewUserFromDto(userDTO, Role.DEFAULT_USER_ROLE)).thenReturn(user);
       when(userRepository.save(any(User.class))).thenReturn(user);


       // Act
       User registeredNewDefaultUser = userService.registerNewDefaultUser(userDTO);

       // Assert
       verify(userRepository, times(1)).existsByUsername("john_doe");
       verify(userFactory, times(1)).createNewUserFromDto(userDTO, Role.DEFAULT_USER_ROLE);
       verify(userRepository, times(1)).save(user);

       assertNotNull(registeredNewDefaultUser);
       assertEquals("john_doe", registeredNewDefaultUser.getUsername());
       assertEquals("password1", registeredNewDefaultUser.getPassword());
       assertTrue(registeredNewDefaultUser.getRoles().contains(role));

   }

   @Test
    void whenUsernameExistWhileRegisteringNewDefaultUser_thenThrowException() {

       // Arrange
       UserDTO userDTO = new UserDTO("existing_user", "password");

       when(userRepository.existsByUsername("existing_user")).thenReturn(true);

       // Act + Assert
       assertThrows(UserAlreadyExistsException.class, () -> {
           userService.registerNewDefaultUser(userDTO);
       });

       verify(userRepository, times(1)).existsByUsername("existing_user");
       verifyNoMoreInteractions(userRepository);

   }

}
