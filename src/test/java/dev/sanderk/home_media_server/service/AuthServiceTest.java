package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.model.Role;
import dev.sanderk.home_media_server.model.User;
import dev.sanderk.home_media_server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtEncoder jwtEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void authenticationForUserLoginTest_Success() {

        // Arrange
        UserDTO userDTO = new UserDTO("john_doe", "password");
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("password");

        Role role = new Role();
        role.setRoleName(Role.DEFAULT_USER_ROLE);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        Jwt jwtToken = new Jwt(
            "mocked-token",
                Instant.now(),
                Instant.now().plusSeconds(3600),
                Map.of("alg", "none"),                     // headers (must not be empty)
                Map.of("sub", "john_doe")
        );

        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userDTO.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwtToken);

        // Act
        String generatedTokenForAuth = authService.autheticationForUserLogin(userDTO);


        // Assert
        verify(userRepository, times(1)).findByUsername(userDTO.getUsername());
        verify(passwordEncoder, times(1)).matches(userDTO.getPassword(), user.getPassword());
        verify(jwtEncoder, times(1)).encode(any(JwtEncoderParameters.class));

        assertEquals("mocked-token", generatedTokenForAuth);

    }










    // generateTokenForUserAuthorizationTest_Success


    // autheticationForUserLoginTest_UserNotFound


    // autheticationForUserLoginTest_InvalidPassword

}
