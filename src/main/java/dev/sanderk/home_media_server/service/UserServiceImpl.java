package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.component.UserFactory;
import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.model.Role;
import dev.sanderk.home_media_server.model.User;
import dev.sanderk.home_media_server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public User registerNewDefaultUser(UserDTO userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            logger.warn("Username {} already exists", userDto.getUsername());
            throw new IllegalArgumentException("Username already exists");
        }

        User user = userFactory.createNewUserFromDto(userDto, Role.DEFAULT_USER_ROLE);
        userRepository.save(user);
        return user;
    }




}
