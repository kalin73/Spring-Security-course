package org.example.seccourse.service;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.model.entity.RoleEntity;
import org.example.seccourse.repository.RoleRepository;
import org.example.seccourse.utils.Role;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;


    public void addRole(String role) {
        this.roleRepository.save(new RoleEntity(role));
    }

    public RoleEntity getRoleByName(String role) {
        return this.roleRepository
                .findByRole(Role.valueOf(role.toUpperCase()))
                .orElseThrow(NoSuchElementException::new);
    }
}
