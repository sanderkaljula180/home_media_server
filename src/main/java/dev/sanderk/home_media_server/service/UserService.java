package dev.sanderk.home_media_server.service;


import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.model.User;

public interface UserService {

    /**
     * Registers a new user with default role and encoded password.
     *
     * @param userDto The user to register.
     * @return User
     */
    User registerNewDefaultUser(UserDTO userDto);


}
