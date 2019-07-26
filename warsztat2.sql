CREATE DATABASE warsztat2krkw03 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
id int AUTO_INCREMENT,
username VARCHAR(255) UNIQUE NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
PRIMARY KEY(id)
);

INSERT INTO users (username, email, password) VALUES ('Mikołaj', 'miki@miki.pl', 'dupa1');

SELECT * FROM warsztat2krkw03.users;

DROP TABLE IF EXISTS user_group;
CREATE TABLE user_group (
id int AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
user_id int NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS exercise;
CREATE TABLE exercise (
id int AUTO_INCREMENT,
title VARCHAR(255) NOT NULL,
description TEXT NOT NULL,
PRIMARY KEY(id)
);

DROP TABLE IF EXISTS solution;
CREATE TABLE solution (
id int(11) AUTO_INCREMENT NOT NULL,
created VARCHAR(255) NOT NULL,
updated VARCHAR(255) NOT NULL,
description TEXT NOT NULL,
exercise_id INT(11) NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE,
FOREIGN KEY(exercise_id) REFERENCES exercise(id) ON DELETE CASCADE
);

