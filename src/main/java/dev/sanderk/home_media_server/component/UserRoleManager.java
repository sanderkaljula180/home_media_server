package dev.sanderk.home_media_server.component;

import dev.sanderk.home_media_server.model.Role;
import dev.sanderk.home_media_server.repository.RoleRepository;
import dev.sanderk.home_media_server.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserRoleManager {

    private final RoleRepository roleRepository;

    public UserRoleManager(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> assignRole(String roleName) {
        Role assignedRole = roleRepository.findByRoleName(roleName)
                .orElseGet(() -> {
                    Role newUserRole = new Role();
                    newUserRole.setRoleName(roleName);
                    return roleRepository.save(newUserRole);
                });

        return Set.of(assignedRole);
    }

}
