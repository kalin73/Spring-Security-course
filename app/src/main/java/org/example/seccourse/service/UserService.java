package org.example.seccourse.service;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.model.dto.StudentDto;
import org.example.seccourse.model.entity.RoleEntity;
import org.example.seccourse.model.entity.UserEntity;
import org.example.seccourse.repository.RoleRepository;
import org.example.seccourse.repository.UserRepository;
import org.example.seccourse.utils.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public String addStudent(StudentDto studentDto) {
        RoleEntity role = this.roleRepository
                .findByRole(Role.valueOf("STUDENT"))
                .orElseThrow(NoSuchElementException::new);

        UserEntity user = UserEntity.builder().username(studentDto.username())
                .password(this.passwordEncoder.encode(studentDto.password()))
                .roles(List.of(role))
                .build();

        this.userRepository.save(user);

        return "Successfully added student!";
    }
}
