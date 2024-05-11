# 임시 유저
desc users;
INSERT INTO users(username, email, password)
VALUES ('test', 'test@email.com', '123');

SELECT *
from users;
# 임시 회원 아이디 1

# 다중 조회
SELECT r.review_id,
       r.content,
       r.tour_id,
       r.created_at,
       r.updated_at,
       r.like_count,
       i.img,
       u.user_id,
       u.nickname,
       u.profile_image
FROM review r
         LEFT JOIN review_img i ON r.review_id = i.review_id
         JOIN users u ON r.author_id = u.user_id
ORDER BY r.created_at DESC;


# 아이디 조회
SELECT r.review_id,
       r.content,
       r.tour_id,
       r.created_at,
       r.updated_at,
       r.like_count,
       i.img,
       u.user_id,
       u.nickname,
       u.profile_image
FROM review r
         LEFT JOIN review_img i ON r.review_id = i.review_id
         JOIN users u ON r.author_id = u.user_id
WHERE r.review_id = 2
ORDER BY r.created_at DESC;


# 리뷰 작성
INSERT INTO review(content, author_id, tour_id)
VALUES ('testContent', 1, 125266);

# 리뷰 수정
UPDATE review
SET content = 'updateContent',
    tour_id=125405
WHERE review_id = 1;

# 리뷰 삭제
DELETE
FROM review
WHERE review_id = 1;

# 리뷰 댓글 목록
SELECT c.comment_id, c.review_id, c.content, u.nickname, c.user_id, c.created_at
FROM review_comment c JOIN users u ON c.user_id = u.user_id
WHERE review_id = 1;

# 리뷰 댓글 특정 조건 조회
SELECT comment_id, review_id, content, user_id, created_at, updated_at
FROM review_comment
WHERE comment_id = 1;

# 리뷰 댓글 작성
INSERT INTO review_comment(review_id, user_id, content)
VALUES (2, 1, 'testComment');

# 리뷰 댓글 수정
UPDATE review_comment
SET content = 'updateContent'
WHERE comment_id = 1;

# 리뷰 댓글 삭제
DELETE
FROM review_comment
WHERE comment_id = 1;

# 리뷰 좋아요 조회
SELECT review_id, user_id
FROM review_like;

# 리뷰 좋아요 특정 조회
SELECT review_id, user_id
FROM review_like
WHERE user_id = 1
  AND review_id = 2;

# 리뷰 좋아요 하기
INSERT INTO review_like(review_id, user_id)
VALUES (2, 1);

# 리뷰 좋아요 취소
DELETE
FROM review_like
WHERE review_id = 2
  AND user_id = 1;

# 리뷰 사진 저장
INSERT INTO review_img (review_id, img)
# VALUES (#{reviewId}, #{imgUrl});

SELECT *
FROM tour;

Show tables;