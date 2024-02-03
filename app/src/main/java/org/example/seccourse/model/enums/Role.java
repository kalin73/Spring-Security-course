package org.example.seccourse.model.enums;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Role {
    STUDENT(new HashSet<>()),
    ADMIN(Set.of(Permissions.COURSE_READ,
            Permissions.COURSE_WRITE,
            Permissions.STUDENT_READ,
            Permissions.STUDENT_WRITE)),

    ADMINTRAINEE(Set.of(Permissions.COURSE_READ,
            Permissions.STUDENT_READ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
