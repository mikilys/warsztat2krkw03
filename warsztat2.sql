CREATE DATABASE warsztat2krkw03 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
id int AUTO_INCREMENT,
username VARCHAR(255) UNIQUE NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
PRIMARY KEY(id)
);

INSERT INTO users (username, email, password) VALUES ('Mikołaj', 'miki@miki.pl', 'dupa1');