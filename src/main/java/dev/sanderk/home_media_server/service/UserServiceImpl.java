package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.component.UserFactory;
import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.exception.UserAlreadyExistsException;
import dev.sanderk.home_media_server.model.Role;
import dev.sanderk.home_media_server.model.User;
import dev.sanderk.home_media_server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserServiceImpl(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public User registerNewDefaultUser(UserDTO userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            log.error("Username already exists",
                    kv("event", "NEW_USER_REGISTRATION"),
                    kv("userId", userDto.getUsername())
            );

            throw new UserAlreadyExistsException("Username " + userDto.getUsername() + " already exists");
        }

        User user = userFactory.createNewUserFromDto(userDto, Role.DEFAULT_USER_ROLE);
        userRepository.save(user);
        return user;
    }
}
