package dev.sanderk.home_media_server.service;

import dev.sanderk.home_media_server.dto.UserDTO;
import dev.sanderk.home_media_server.exception.InvalidCredentialsException;
import dev.sanderk.home_media_server.model.User;
import dev.sanderk.home_media_server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Collectors;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder encoder;


    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.encoder = encoder;
    }

    @Override
    public String autheticationForUserLogin(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername())
                .orElseGet(() -> {
                   log.error("Username not found",
                           kv("event", "USER_AUTHENTICATION_FOR_LOGIN"),
                           kv("userId", userDTO.getUsername())
                           );
                   throw new UsernameNotFoundException("User not found: " + userDTO.getUsername());
                });

        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            log.error("Password match failed",
                    kv("event", "USER_AUTHENTICATION_FOR_LOGIN"),
                    kv("userId", userDTO.getUsername())
            );
            throw new InvalidCredentialsException("Invalid username or password");
        }

        Collection<? extends GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .toList();
        String principal = user.getUsername();
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, authorities);

        return generateTokenForUserAuthorization(authentication);
    }

    @Override
    public String generateTokenForUserAuthorization(Authentication authentication) {
        log.info("Generating token",
                kv("event", "USER_AUTHENTICATION_FOR_LOGIN"),
                kv("userId", authentication.getName())
        );

        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

}
