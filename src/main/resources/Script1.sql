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