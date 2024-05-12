SELECT * from review;
SELECT * from users;
SELECT * from review_img;

INSERT INTO review(content, author_id, tour_id) VALUES ('설악산 국립 공원 강추드립니다 꼭 가보세요.', 3, 125584);
INSERT INTO review_img(review_id, img) VALUES (LAST_INSERT_ID(), 'http://tong.visitkorea.or.kr/cms/resource/43/780843_image2_1.jpg');

INSERT INTO review(content, author_id, tour_id) VALUES ('단경골 휴양지...좋습니다,,', 4, 125636);
insert into review_img values(LAST_INSERT_ID(),'http://tong.visitkorea.or.kr/cms/resource/60/205360_image2_1.JPG');

INSERT INTO review(content, author_id, tour_id) VALUES ('당진면천읍성..후기...', 5, 125962);
insert into review_img values(LAST_INSERT_ID(),'http://tong.visitkorea.or.kr/cms/resource/68/533468_image2_1.jpg');

INSERT INTO review(content, author_id, tour_id) VALUES ('혼자 여행 다녀왔는데 좋았어요 강추드립니다.', 6, 125944);
insert into review_img values(LAST_INSERT_ID(),'http://tong.visitkorea.or.kr/cms/resource/07/1601507_image2_1.jpg');

INSERT INTO review(content, author_id, tour_id) VALUES ('등산은,, 좋은 것 입니다..', 7, 125463);
insert into review_img values(LAST_INSERT_ID(),'http://tong.visitkorea.or.kr/cms/resource/29/1900129_image2_1.jpg');
