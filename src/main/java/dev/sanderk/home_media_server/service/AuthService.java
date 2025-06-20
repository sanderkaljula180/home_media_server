package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.dto.UserDTO;
import org.springframework.security.core.Authentication;

public interface AuthService {

    /**
     * Logins user. Checks for matching username and password.
     *
     * @param userDto The user to authenticate.
     * @return generated token
     */
    String autheticationForUserLogin(UserDTO userDto);

    /**
     * This generates new jwt token for logged in user authorization
     *
     * @param authentication for generating jwt token
     * @return generated token
     */
    String generateTokenForUserAuthorization(Authentication authentication);
}
