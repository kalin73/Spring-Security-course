package org.example.seccourse.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.seccourse.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntity implements GrantedAuthority {
    @Enumerated(EnumType.STRING)
    private Role role;

    public RoleEntity(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    @Override
    public String getAuthority() {
        return this.role.name();
    }
}
