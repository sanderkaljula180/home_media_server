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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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

    @Test
    void generateTokenForUserAuthorizationTest_Success() {
        // Arrange
        Set<SimpleGrantedAuthority> authorities = Set.of(
                new SimpleGrantedAuthority("ROLE_USER")
        );
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "test_user",
                null,
                authorities
        );

        Instant now = Instant.now();
        Jwt jwtToken = new Jwt(
                "mocked-token",
                now,
                now.plus(1, ChronoUnit.HOURS),
                Map.of("alg", "none"),
                Map.of(
                        "iss", "self",
                        "sub", "test_user",
                        "scope", "ROLE_USER"
                )
        );

        when(jwtEncoder.encode(any(JwtEncoderParameters.class)))
                .thenReturn(jwtToken);

        // Act
        String token = authService.generateTokenForUserAuthorization(authentication);

        // Assert
        assertNotNull(token);
        assertEquals("mocked-token", token);
        verify(jwtEncoder, times(1)).encode(any(JwtEncoderParameters.class));

    }

    @Test
    void autheticationForUserLoginTest_UserNotFound() {
        // Arrange
        UserDTO userDTO = new UserDTO("non_existent_user", "password");
        String expectedErrorMessage = "User not found: " + userDTO.getUsername();

        when(userRepository.findByUsername(userDTO.getUsername()))
                .thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            authService.autheticationForUserLogin(userDTO);
        });

        // Verify the error message
        assertEquals(expectedErrorMessage, exception.getMessage());

        verify(userRepository, times(1)).findByUsername(userDTO.getUsername());
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(jwtEncoder);
    }

    @Test
    void autheticationForUserLoginTest_InvalidPassword() {
        // Arrange
        UserDTO userDTO = new UserDTO("john_doe", "wrong_password");
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("correct_password");

        when(userRepository.findByUsername(userDTO.getUsername()))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
                .thenReturn(false);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            authService.autheticationForUserLogin(userDTO);
        });

        verify(userRepository, times(1)).findByUsername(userDTO.getUsername());
        verify(passwordEncoder, times(1))
                .matches(userDTO.getPassword(), user.getPassword());
        verifyNoInteractions(jwtEncoder);
    }


}
