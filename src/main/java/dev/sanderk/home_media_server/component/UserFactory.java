package dev.sanderk.home_media_server.component;

import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final PasswordEncoder passwordEncoder;
    private final UserRoleManager userRoleManager;

    public UserFactory(PasswordEncoder passwordEncoder, UserRoleManager userRoleManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRoleManager = userRoleManager;
    }

    public User createNewUserFromDto(UserDTO userDto, String role) {

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userRoleManager.assignRole(role));

        return user;
    }
}
