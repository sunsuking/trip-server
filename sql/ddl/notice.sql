DROP TABLE IF EXISTS notices CASCADE;
DROP TABLE IF EXISTS notices_images CASCADE;

# 공지사항 테이블
CREATE TABLE notices
(
    notice_id  INTEGER PRIMARY KEY AUTO_INCREMENT,
    title      VARCHAR(255)                        NOT NULL,
    content    TEXT                                NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

# 공지사항 사진 테이블
# CREATE TABLE notices_images
# (
#     notice_id INTEGER NOT NULL,
#     image VARCHAR(500) NOT NULL ,
#     PRIMARY KEY (notice_id, image),
#     CONSTRAINT foreign_key_notice_img_notice_id FOREIGN KEY (notice_id) REFERENCES notices (notice_id) ON DELETE CASCADE
# )
