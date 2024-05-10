SET foreign_key_checks = 0;
CREATE DATABASE IF NOT EXISTS ssafytrip;
use ssafytrip;

-- DROP TABLE
DROP TABLE IF EXISTS trip;
DROP TABLE IF EXISTS board_like;
DROP TABLE IF EXISTS board_comment;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tour_detail;
DROP TABLE IF EXISTS tour;
DROP TABLE IF EXISTS town;
DROP TABLE IF EXISTS city;

-- CREATE TABLE
CREATE TABLE users
(
    user_id                INTEGER PRIMARY KEY AUTO_INCREMENT,
    username          VARCHAR(255)                                                                   NOT NULL,
    email             VARCHAR(255)                                                                   NOT NULL,
    password          VARCHAR(255)                                                                   NOT NULL,
    nickname          VARCHAR(255)                                                                   NULL,
    profile_image     VARCHAR(500)                                                                   NULL,
    provider_type     enum ('LOCAL', 'GOOGLE', 'GITHUB', 'KAKAO', 'NAVER') DEFAULT 'LOCAL'           NOT NULL,
    role_type         enum ('USER', 'ADMIN')                               DEFAULT 'USER'            NOT NULL,
    is_email_verified BOOLEAN                                              DEFAULT FALSE             NOT NULL,
    is_locked         BOOLEAN                                              DEFAULT FALSE             NOT NULL,
    created_at        TIMESTAMP                                            DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at        TIMESTAMP                                            DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT users_email_unique UNIQUE (email),
    CONSTRAINT users_username_unique UNIQUE (username)
);

DROP TABLE IF EXISTS plan;
CREATE TABLE IF NOT EXISTS plan (
                                    plan_id INT PRIMARY KEY AUTO_INCREMENT,
                                    user_id INT NOT NULL,
                                    name VARCHAR(100) NOT NULL,
                                    start_at DATE NOT NULL,
                                    end_at DATE NOT NULL,
                                    like_count INT DEFAULT 0,
                                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS plan_like (
                                         plan_id INT NOT NULL,
                                         user_id INT NOT NULL,
                                         PRIMARY KEY (plan_id, user_id),
                                         CONSTRAINT foreign_key_plan_like_plan_id FOREIGN KEY (plan_id) REFERENCES plan(plan_id) ON DELETE CASCADE,
                                         CONSTRAINT foreign_key_plan_like_user_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS board (
                                     board_id INT PRIMARY KEY AUTO_INCREMENT,
                                     title VARCHAR(100) NOT NULL,
                                     content TEXT NOT NULL,
                                     author INT NOT NULL,
                                     created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                     updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     view_count INT DEFAULT 0,
                                     like_count INT DEFAULT 0,
                                     is_notice BOOLEAN DEFAULT FALSE,
                                     CONSTRAINT foreign_key_board_author FOREIGN KEY (author) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS board_comment (
                                             comment_id INT PRIMARY KEY AUTO_INCREMENT,
                                             board_id INT NOT NULL,
                                             content TEXT NOT NULL,
                                             author INT NOT NULL,
                                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                             parent INT,
                                             CONSTRAINT foreign_key_board_comment_board_id FOREIGN KEY (board_id) REFERENCES board(board_id) ON DELETE CASCADE,
                                             CONSTRAINT foreign_key_board_comment_author FOREIGN KEY (author) REFERENCES users(user_id) ON DELETE CASCADE,
                                             CONSTRAINT foreign_key_board_comment_parent FOREIGN KEY (parent) REFERENCES board_comment(comment_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS board_like (
                                          board_id INT NOT NULL,
                                          user_id INT NOT NULL,
                                          PRIMARY KEY (board_id, user_id),
                                          CONSTRAINT foreign_key_board_like_board_id FOREIGN KEY (board_id) REFERENCES board(board_id) ON DELETE CASCADE,
                                          CONSTRAINT foreign_key_board_like_user_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tour_content (
                                            content_id INT PRIMARY KEY AUTO_INCREMENT,
                                            name ENUM("TOURIST_SPOT", "STAY", "RESTAURANT", "CULTURE", "SHOW", "TRAVEL", "SHOPPING", "LEISURE") NOT NULL
);

CREATE TABLE IF NOT EXISTS city (
                                    city_code INT PRIMARY KEY,
                                    name VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS town;
CREATE TABLE IF NOT EXISTS town (
                                    town_code INT,
                                    name VARCHAR(30) NOT NULL,
                                    city_code INT,
                                    PRIMARY KEY (town_code, city_code),
                                    CONSTRAINT foreign_key_town_city_code FOREIGN KEY (city_code) REFERENCES city(city_code) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tour (
                                    tour_id INT PRIMARY KEY AUTO_INCREMENT,
                                    content_type_id INT NOT NULL,
                                    name VARCHAR(100) NOT NULL,
                                    address VARCHAR(200) NOT NULL,
                                    zip_code VARCHAR(10) NULL,
                                    background_image VARCHAR(200) NULL,
                                    city_code INT NULL,
                                    town_code INT NULL,
                                    hit INT DEFAULT 0,
                                    CONSTRAINT foreign_key_tour_content_type_id FOREIGN KEY (content_type_id) REFERENCES tour_content(content_id) ON DELETE CASCADE,
                                    CONSTRAINT foreign_key_tour_city_code FOREIGN KEY (city_code) REFERENCES city(city_code) ON DELETE CASCADE,
                                    CONSTRAINT foreign_key_tour_town_code FOREIGN KEY (town_code) REFERENCES town(town_code) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tour_detail (
                                           tour_id INT PRIMARY KEY,
                                           description TEXT NOT NULL,
                                           telephone VARCHAR(20) NULL,
                                           latitude DECIMAL(11, 8) NULL,
                                           longitude DECIMAL(11, 8) NULL,
                                           CONSTRAINT foreign_key_tour_detail_tour_id FOREIGN KEY (tour_id) REFERENCES tour(tour_id) ON DELETE CASCADE
);

create table trip (
                      plan_id     int           not null,
                      tour_id     int           not null,
                      start_at    datetime      null,
                      end_at      datetime      null,
                      price       int default 0 not null,
                      description text          null,
                      primary key (plan_id, tour_id),
                      constraint foreign_key_trip_plan_id
                          foreign key (plan_id) references ssafytrip.plan (plan_id)
                              on delete cascade,
                      constraint foreign_key_trip_tour_id
                          foreign key (tour_id) references ssafytrip.tour (tour_id) on delete cascade
);

SHOW TABLES;
SET foreign_key_checks = 1;