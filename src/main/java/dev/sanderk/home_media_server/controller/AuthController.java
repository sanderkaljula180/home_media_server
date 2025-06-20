package dev.sanderk.home_media_server.controller;

import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.service.AuthServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUserWithJWT(@RequestBody @Valid UserDTO userDTO) {

        String authorizedUserToken = authService.autheticationForUserLogin(userDTO);
        return new ResponseEntity<>(authorizedUserToken, HttpStatus.CREATED);
    }

    // TÖÖTAS
    @PostMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("TÖÖTAB", HttpStatus.ACCEPTED);
    }


}
