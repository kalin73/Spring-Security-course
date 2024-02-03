package org.example.seccourse.web.rest;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.model.dto.StudentDto;
import org.example.seccourse.model.dto.StudentRegisterForm;
import org.example.seccourse.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
@RequiredArgsConstructor
public class StudentManagementController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<StudentDto> getAllStudents() {
        return this.userService.getAllStudents();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public String addStudent(@RequestBody StudentRegisterForm student) {
        return this.userService.addStudent(student);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('student:write')")
    public String deleteStudentById(@RequestParam("id") Long id) {
        return this.userService.deleteStudentById(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('student:write')")
    public String updateStudentById(@RequestParam("id") Long id, @RequestBody StudentDto student) {
        return this.userService.updateStudentById(id, student);
    }
}
