CREATE TABLE roles
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    role ENUM ('STUDENT', 'TEACHER', 'ADMIN')
);

CREATE TABLE users
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(55)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE users_roles
(
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);