package dev.sanderk.home_media_server.repository;

import dev.sanderk.home_media_server.model.Role;
import dev.sanderk.home_media_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String user);
}
