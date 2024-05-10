# 유저 테이블
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS notices CASCADE;
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
    notice_id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    title      VARCHAR(255)                        NOT NULL,
    content    TEXT                                NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
)

