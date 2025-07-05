package dev.sanderk.home_media_server.controller;

import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final UserServiceImpl userService;

    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerNewUser(@RequestBody @Valid UserDTO userDto) {
        userService.registerNewDefaultUser(userDto);

        log.info("Successful registration",
                kv("event", "NEW_USER_REGISTRATION"),
                kv("userId", userDto.getUsername())
        );

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }

}
