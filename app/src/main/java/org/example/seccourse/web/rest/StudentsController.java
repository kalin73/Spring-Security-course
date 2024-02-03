package org.example.seccourse.web.rest;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.model.dto.StudentDto;
import org.example.seccourse.model.dto.StudentRegisterForm;
import org.example.seccourse.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class StudentsController {
    private final UserService userService;


    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        return this.userService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public StudentRegisterForm getStudentById(@PathVariable(name = "id") int id) {
        return null;
    }

    @GetMapping("/addStudent")
    public String addStudent(@RequestBody StudentRegisterForm student) {
        return this.userService.addStudent(student);
    }
}
