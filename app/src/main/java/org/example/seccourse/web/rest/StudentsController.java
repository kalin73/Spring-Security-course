package org.example.seccourse.web.rest;

import org.example.seccourse.model.dto.StudentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/")
public class StudentsController {
    private static final List<StudentDto> STUDENTS = List.of(new StudentDto(1, "Ivan"),
            new StudentDto(2, "Pesho"),
            new StudentDto(3, "Gosho"));

    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        return STUDENTS;
    }

    @GetMapping("/students/{id}")
    public StudentDto getStudentById(@PathVariable(name = "id") int id) {
        return STUDENTS.stream().filter(s -> s.studentId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Student with ID: " + id + " was not found!"));
    }
}
