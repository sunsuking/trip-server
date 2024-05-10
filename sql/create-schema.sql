# 유저 테이블
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    user_id           INTEGER PRIMARY KEY AUTO_INCREMENT,
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

# 공지사항 테이블
CREATE TABLE notices
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    title      VARCHAR(255)                        NOT NULL,
    content    TEXT                                NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE VIEW tour_with_address AS
(
SELECT T.tour_id,
       T.content_type_id,
       TC.name AS content_type,
       T.name,
       address,
       zip_code,
       background_image,
       C.name  AS city,
       O.name  AS town
FROM tour T
         LEFT JOIN tour_content TC ON T.content_type_id = TC.content_id
         LEFT JOIN city C ON T.city_code = C.city_code
         LEFT JOIN town O ON O.town_code = T.town_code AND O.city_code = T.city_code
    );

CREATE VIEW tour_with_content AS
(
SELECT T.tour_id,
       T.content_type_id,
       TC.name AS content_type,
       T.name,
       address,
       zip_code,
       background_image,
       C.name  AS city,
       O.name  AS town,
       TD.description,
       TD.telephone,
       TD.latitude,
       TD.longitude
FROM tour T
         LEFT JOIN tour_content TC ON T.content_type_id = TC.content_id
         LEFT JOIN city C ON T.city_code = C.city_code
         LEFT JOIN town O ON O.town_code = T.town_code AND O.city_code = T.city_code
         LEFT JOIN tour_detail TD ON T.tour_id = TD.tour_id
    );
