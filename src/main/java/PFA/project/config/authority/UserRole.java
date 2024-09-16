package PFA.project.config.authority;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    SYSTEM(
            Set.of(

            )
    ),
    STUDENT(
            Set.of(
            )
    ),
    TEACHER(
            Set.of(

            )
    );
    @Getter
    private final Set<Permissions> permissions;

    UserRole(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }


}
