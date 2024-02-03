package org.example.seccourse.web.rest;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.model.dto.StudentDto;
import org.example.seccourse.model.dto.StudentRegisterForm;
import org.example.seccourse.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
@RequiredArgsConstructor
public class StudentManagementController {
    private final UserService userService;

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return this.userService.getAllStudents();
    }

    @PostMapping
    public String addStudent(@RequestBody StudentRegisterForm student) {
        return this.userService.addStudent(student);
    }

    @DeleteMapping
    public String deleteStudentById(@RequestParam("id") Long id) {
        return this.userService.deleteStudentById(id);
    }

    @PutMapping
    public String updateStudentById(@RequestParam("id") Long id, @RequestBody StudentDto student) {
        return this.userService.updateStudentById(id, student);
    }
}
