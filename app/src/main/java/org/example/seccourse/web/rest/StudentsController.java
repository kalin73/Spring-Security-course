package org.example.seccourse.web.rest;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.model.dto.StudentDto;
import org.example.seccourse.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class StudentsController {
    private final UserService userService;

    @GetMapping("/students/{id}")
    public StudentDto getStudentById(@PathVariable(name = "id") Long id) {
        return this.userService.getStudentById(id);
    }


}
