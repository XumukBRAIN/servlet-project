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
    birthdate TIMESTAMP,
    faculty VARCHAR NOT NULL,
    homeroom_teacher_id INT,
    FOREIGN KEY (homeroom_teacher_id) REFERENCES homeroom_teacher(id),
    PRIMARY KEY(id)
);

INSERT INTO homeroom_teacher (id, firstname, secondname, patronymic, specialization) VALUES (1, 'Иван', 'Кудряшов', 'Дмитриевич', 'Информатика');
INSERT INTO student (id, firstname, secondname, patronymic, birthdate, faculty, homeroom_teacher_id) VALUES (1, 'Петр', 'Иванов', 'Иванович', null, 'Информационные технологии', 1);
