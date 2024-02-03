package org.example.seccourse.service;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.model.dto.StudentDto;
import org.example.seccourse.model.dto.StudentRegisterForm;
import org.example.seccourse.model.entity.RoleEntity;
import org.example.seccourse.model.entity.UserEntity;
import org.example.seccourse.repository.RoleRepository;
import org.example.seccourse.repository.UserRepository;
import org.example.seccourse.model.enums.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public String addStudent(StudentRegisterForm studentRegisterForm) {
        RoleEntity role = this.roleRepository
                .findByRole(Role.valueOf("STUDENT"))
                .orElseThrow(NoSuchElementException::new);

        UserEntity user = UserEntity.builder().username(studentRegisterForm.username())
                .password(this.passwordEncoder.encode(studentRegisterForm.password()))
                .roles(Set.of(role))
                .build();

        this.userRepository.save(user);

        return "Successfully added student!";
    }

    public List<StudentDto> getAllStudents() {
        return this.userRepository
                .findAll()
                .stream()
                .filter(s -> s.getRoles()
                        .stream()
                        .anyMatch(r -> r.getRole().name().equals("STUDENT")))
                .map(StudentDto::mapToStudentDto)
                .toList();
    }

    public StudentDto getStudentById(Long id) {
        return this.userRepository.findById(id)
                .map(StudentDto::mapToStudentDto)
                .orElseThrow(() -> new UsernameNotFoundException("Student with ID: " + id + " does not exist!"));
    }

    public String deleteStudentById(Long id) {
        this.userRepository.deleteById(id);

        return "Student was deleted successfully!";
    }

    public String updateStudentById(Long id, StudentDto studentDto) {
        UserEntity user = this.userRepository.findById(id).orElseThrow();

        user.setUsername(studentDto.username());

        this.userRepository.save(user);

        return "Student was updated successfully!";
    }
}
