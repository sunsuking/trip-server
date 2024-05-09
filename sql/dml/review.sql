# 임시 유저
desc users;
INSERT INTO users(username,email,password) VALUES('test','test@email.com','123');

SELECT * from users;
# 임시 회원 아이디 1

# 다중 조회
SELECT review_id, title, content, author_id, tour_id, created_at, updated_at, like_count
FROM review;

# 작성자 조회
SELECT review_id, title, content, author_id, tour_id, created_at, updated_at, like_count
FROM review
WHERE author_id = 1;

desc review;
select * from tour limit 2;

# 리뷰 작성
INSERT INTO review(title,content, author_id,tour_id)
VALUES ('testTitle','testContent',1,125266);

# 리뷰 수정
UPDATE review
SET title = 'updateTitle', content = 'updateContent',tour_id=125405
WHERE review_id = 1;

# 리뷰 삭제
DELETE FROM review
WHERE review_id = 1;

# 리뷰 댓글 목록
SELECT comment_id, review_id, content, author_id, created_at, updated_at
FROM review_comment;

# 리뷰 댓글 특정 조건 조회
SELECT comment_id, review_id, content, author_id, created_at, updated_at
FROM review_comment
WHERE comment_id = 1;

# 리뷰 작성
INSERT INTO review_comment(review_id, content, author_id)
VALUES (2,'testComment',1);

# 리뷰 수정
UPDATE review_comment
SET content = 'updateContent'
WHERE comment_id = 1;

# 리뷰 삭제
DELETE FROM review_comment
WHERE comment_id = 1;

# 리뷰 좋아요 조회
SELECT review_id, user_id
FROM review_like;

# 리뷰 좋아요 특정 조회
SELECT review_id, user_id
FROM review_like
WHERE user_id = 1 AND review_id = 2;

# 리뷰 좋아요 하기
INSERT INTO review_like(review_id, user_id)
VALUES (2,1);

# 리뷰 좋아요 취소
DELETE FROM review_like
WHERE review_id = 2 AND user_id = 1;