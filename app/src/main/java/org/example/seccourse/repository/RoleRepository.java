package org.example.seccourse.repository;

import org.example.seccourse.model.entity.RoleEntity;
import org.example.seccourse.utils.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(Role role);
}
