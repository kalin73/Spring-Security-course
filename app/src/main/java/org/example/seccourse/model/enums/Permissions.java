package org.example.seccourse.model.enums;

public enum Permissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    public final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return this.permission;
    }
}
