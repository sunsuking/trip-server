DROP TABLE IF EXISTS notices CASCADE;
# 공지사항 테이블
CREATE TABLE notices
(
    notice_id  INTEGER PRIMARY KEY AUTO_INCREMENT,
    title      VARCHAR(255)                        NOT NULL,
    content    TEXT                                NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL
);

