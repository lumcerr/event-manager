CREATE DATABASE IF NOT EXISTS KasvAcademic;
USE KasvAcademic;

DROP TABLE IF EXISTS student;

CREATE TABLE student (
	id int NOT NULL AUTO_INCREMENT,
    first_name varchar(45) default null,
    last_name varchar(45) default null,
    email varchar(45) default null,
    PRIMARY KEY (id)

) auto_increment=1 default charset=UTF8;

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

CREATE TABLE instructor_detail (
    id int NOT NULL AUTO_INCREMENT,
    youtube_channel VARCHAR(45) DEFAULT NULL,
    hobby VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (id)
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS instructor;

CREATE TABLE If not exists instructor (
    id int NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(45) DEFAULT NULL,
    last_name VARCHAR(45) DEFAULT NULL,
    email VARCHAR(45) DEFAULT NULL,
    instructor_detail_id int DEFAULT NULL,
    PRIMARY KEY (id),
    KEY FK_DETAIL_idx (instructor_detail_id),
    CONSTRAINT FK_DETAIL FOREIGN KEY (instructor_detail_id)
    REFERENCES instructor_detail (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

INSERT INTO instructor_detail (id, youtube_channel, hobby) VALUES
(1, 'TechGuru', 'Programming'),
(2, 'FitnessFreak', 'Gym'),
(3, 'TravelVlogger', 'Traveling'),
(4, 'CookingMaster', 'Cooking'),
(5, 'MusicLover', 'Guitar');

INSERT INTO instructor (first_name, last_name, email, instructor_detail_id) VALUES
('Johny', 'Doey', 'johny.doey@example.com', 1),
('Janee', 'Smiteh', 'janes.smith@example.com', 2),
('Mikef', 'Johnsonf', 'mikef.johnson@example.com', 3),
('Emilys', 'Daviss', 'emilys.davis@example.com', 4),
('Chrisy', 'Browny', 'chrisy.brown@example.com', 5);

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
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

INSERT INTO course (id, title, instructor_id) VALUES
(1, "tennis", 2),
(2, "basketball", 6),
(3, "CSS", 3);

DROP TABLE IF EXISTS review;

CREATE TABLE review (
	id int NOT NULL auto_increment,
    comment_i varchar(256) default null,
    course_id int default null,

    PRIMARY KEY (id),

    KEY FK_COURSE_ID_idx (course_id),

    CONSTRAINT FK_COURSE
    foreign key (course_id)
    references course (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) auto_increment=1 default CHARSET=UTF8;

DROP TABLE IF EXISTS course_student;

CREATE TABLE course_student (
    course_id int NOT NULL,
    student_id int NOT NULL,

    PRIMARY KEY (course_id, student_id),

    KEY FK_STUDENT_idx (student_id),

    CONSTRAINT FK_COURSE_O5 FOREIGN KEY (course_id)
    REFERENCES course (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_STUDENT FOREIGN KEY (student_id)
    REFERENCES student (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) DEFAULT CHARSET=UTF8;

INSERT INTO course_student (course_id, student_id) VALUES
(3, 11),
(3, 10);