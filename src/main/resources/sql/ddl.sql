CREATE TABLE homeroom_teacher (
    id INTEGER GENERATED ALWAYS AS IDENTITY,
    firstname VARCHAR NOT NULL,
    secondname VARCHAR NOT NULL,
    patronymic VARCHAR NOT NULL,
    specialization VARCHAR NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE student (
    id INTEGER GENERATED ALWAYS AS IDENTITY,
    firstname VARCHAR NOT NULL,
    secondname VARCHAR NOT NULL,
    patronymic VARCHAR NOT NULL,
    birthdate TIMESTAMP NOT NULL,
    faculty VARCHAR NOT NULL,
    homeroom_teacher_id INT,
    FOREIGN KEY (homeroom_teacher_id) REFERENCES homeroom_teacher(id),
    PRIMARY KEY(id)
);
