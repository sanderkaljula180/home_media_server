package dev.sanderk.home_media_server.controller;

import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final UserServiceImpl userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerNewUser(@RequestBody @Valid UserDTO userDto) {
        logger.info("NEW USER REGISTRATION: {}", userDto.getUsername());
        userService.registerNewDefaultUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }

}
