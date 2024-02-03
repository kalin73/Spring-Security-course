package org.example.seccourse.web.rest;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.model.dto.StudentDto;
import org.example.seccourse.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class StudentsController {
    private final UserService userService;


    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        return null;
    }

    @GetMapping("/students/{id}")
    public StudentDto getStudentById(@PathVariable(name = "id") int id) {
        return null;
    }

    @GetMapping("/addStudent")
    public String addStudent(@RequestBody StudentDto student) {
        return this.userService.addStudent(student);
    }
}
