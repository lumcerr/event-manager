CREATE DATABASE IF NOT EXISTS KasvAcademy ;
USE KasvAcademy ;

DROP TABLE IF EXISTS student;

CREATE TABLE student (
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(45) DEFAULT NULL,
    last_name varchar(45) DEFAULT NULL,
    email varchar(45) DEFAULT NULL,
    PRIMARY KEY (id)
) AUTO_INCREMENT=1, DEFAULT CHARSET=utf8;

INSERT INTO student VALUES (1, 'John', 'Doe', 'john.doe@gmail.com');
INSERT INTO student VALUES (2, 'Jane', 'Doe', 'jane.doe@gmail.com');
INSERT INTO student VALUES (3, 'Mary', 'Smith', 'mary.smith@gmail.com');
INSERT INTO student VALUES (4, 'Mike', 'Smith', 'mike.smith@gmail.com');
INSERT INTO student VALUES (5, 'Tom', 'Brown', 'tom.brown@gmail.com');
INSERT INTO student VALUES (6, 'Alice', 'Johnson', 'alice.johnson@gmail.com');
INSERT INTO student VALUES (7, 'Bob', 'Williams', 'bob.williams@gmail.com');
INSERT INTO student VALUES (8, 'Charlie', 'Jones', 'charlie.jones@gmail.com');
INSERT INTO student VALUES (9, 'Daisy', 'Taylor', 'daisy.taylor@gmail.com');
INSERT INTO student VALUES (10, 'Eve', 'Martin', 'eve.martin@gmail.com');
INSERT INTO student VALUES (11, 'Frank', 'Lee', 'frank.lee@gmail.com');
INSERT INTO student VALUES (12, 'Grace', 'Walker', 'grace.walker@gmail.com');
INSERT INTO student VALUES (13, 'Henry', 'Young', 'henry.young@gmail.com');
INSERT INTO student VALUES (14, 'Ivy', 'King', 'ivy.king@gmail.com');
INSERT INTO student VALUES (15, 'Jack', 'Wright', 'jack.wright@gmail.com');

DROP TABLE IF EXISTS instructor_detail;

CREATE TABLE instructor_detail(
    id int NOT NULL AUTO_INCREMENT,
    youtube_channel varchar(128) DEFAULT NULL,
    hobby varchar(45) DEFAULT NULL,
    PRIMARY KEY (id)
)AUTO_INCREMENT=1, DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS instructor;

CREATE TABLE instructor (
    id int NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(45) DEFAULT NULL,
    last_name VARCHAR(45) DEFAULT NULL,
    email VARCHAR(45) DEFAULT NULL,
    instructor_detail_id int DEFAULT NULL,
    PRIMARY KEY (id),
    KEY FK_DETAIL_idx (instructor_detail_id),
    CONSTRAINT FK_DETAIL FOREIGN KEY (instructor_detail_id)
    REFERENCES instructor_detail (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS course;

CREATE TABLE IF NOT EXISTS course (
    id int NOT NULL AUTO_INCREMENT,
    title VARCHAR(128) DEFAULT NULL,
    instructor_id int DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY TITLE_UNIQUE (title),
    KEY FK_INSTRUCTOR_idx (instructor_id),
    CONSTRAINT FK_INSTRUCTOR
    FOREIGN KEY (instructor_id)
    REFERENCES instructor (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) AUTO_INCREMENT=10 DEFAULT CHARSET=UTF8

DROP TABLE IF EXISTS review;
CREATE TABLE review (
    id int NOT NULL AUTO_INCREMENT,
    comment varchar(256) DEFAULT NULL,
    course_id int DEFAULT NULL,

    PRIMARY KEY (id)

    KEY FK_COURSE_ID_id (course_id),

    CONSTRAINT FK_COURSE
    FOREIGN_KEY (course_id)
    REFERENCES course (id)

    ON DELETE NO ACTION ON UPDATE NO ACTION
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

CREATE TABLE course_student (
course_id int NOT NULL,
student_id int NOT NULL,

PRIMARY KEY (course_id,student_id),
KEY FK_STUDENT_id (student_id),

CONSTRAINT FK_COURSE FOREIGN KEY (course_id)
REFERENCES course (id)
ON DELETE NO ACTION ON UPDATE NO ACTION

CONSTRAINT FK_STUDENT FOREIGN KEY (student_id)
REFERENCES student (id)
ON DELETE NO ACTION ON UPDATE NO ACTION

 ) DEFAULT CHARSET=UTF8

INSERT INTO instructor VALUES (1, 'John', 'Doe', 'john.doe@gmail.com', 1);
INSERT INTO instructor VALUES (2, 'Jane', 'Smith', 'jane.smith@gmail.com', 2);
INSERT INTO instructor VALUES (3, 'Michael', 'Brown', 'michael.brown@gmail.com', 3);
INSERT INTO instructor VALUES (4, 'Emily', 'Davis', 'emily.davis@gmail.com', 4);
INSERT INTO instructor VALUES (5, 'David', 'Wilson', 'david.wilson@gmail.com', 5);



INSERT INTO instructor_detail VALUES (1, 'TechTalks', 'Photography');
INSERT INTO instructor_detail VALUES (2, 'CodeMasters', 'Gaming');
INSERT INTO instructor_detail VALUES (3, 'LearnAndGrow', 'Traveling');
INSERT INTO instructor_detail VALUES (4, 'AIInsights', 'Cooking');
INSERT INTO instructor_detail VALUES (5, 'DataDive', 'Cycling');

