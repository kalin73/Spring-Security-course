package org.example.seccourse.model.enums;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

@Getter
public enum Role {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(Permissions.COURSE_READ,
            Permissions.COURSE_WRITE,
            Permissions.STUDENT_READ,
            Permissions.STUDENT_WRITE)),

    ADMINTRAINEE(Sets.newHashSet(Permissions.COURSE_READ,
          Permissions.STUDENT_READ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }
}
