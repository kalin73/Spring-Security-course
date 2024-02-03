package org.example.seccourse.model.dto;

import org.example.seccourse.model.entity.UserEntity;

public record StudentDto(Long id, String username) {
    public static StudentDto mapToStudentDto(UserEntity userEntity) {
        return new StudentDto(userEntity.getId(), userEntity.getUsername());
    }
}
