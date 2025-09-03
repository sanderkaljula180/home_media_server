package dev.sanderk.home_media_server.controller;

import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.service.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUserWithJWT(@RequestBody @Valid UserDTO userDTO) {
        String authorizedUserToken = authService.autheticationForUserLogin(userDTO);
        log.info("Successful authentication",
                kv("event", "USER_AUTHENTICATION_FOR_LOGIN"),
                kv("userId", userDTO.getUsername())
        );

        return new ResponseEntity<>(authorizedUserToken, HttpStatus.CREATED);
    }

    // TÖÖTAS
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("TÖÖTAB", HttpStatus.ACCEPTED);
    }


}
