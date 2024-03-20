CREATE DATABASE users_db;
CREATE TABLE homeroom_teacher (
    id INTEGER,
    firstname VARCHAR NOT NULL,
    secondname VARCHAR NOT NULL,
    patronymic VARCHAR NOT NULL,
    specialization VARCHAR NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE student (
    id INTEGER,
    firstname VARCHAR NOT NULL,
    secondname VARCHAR NOT NULL,
    patronymic VARCHAR NOT NULL,
    birthdate TIMESTAMP NOT NULL,
    faculty VARCHAR NOT NULL,
    homeroom_teacher_id INT,
    FOREIGN KEY (homeroom_teacher_id) REFERENCES homeroom_teacher(id),
    PRIMARY KEY(id)
);

INSERT INTO homeroom_teacher (id, firstname, secondname, patronymic, specialization) VALUES (1, 'dsf', 'dfdfg', 'dsfs', 'dsfs')
