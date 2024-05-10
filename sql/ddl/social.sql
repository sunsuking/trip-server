# 소셜 테이블
DROP TABLE IF EXISTS socials CASCADE ;
CREATE TABLE socials
(
    social_id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id           INTEGER NOT NULL,
    title      VARCHAR(255)                        NOT NULL,
    content    TEXT                                NULL,
    view_count INTEGER DEFAULT 0 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT user_id_foreign_key
        FOREIGN KEY (user_id)
            REFERENCES users(user_id) ON UPDATE CASCADE
);