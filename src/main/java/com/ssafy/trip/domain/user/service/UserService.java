package com.ssafy.trip.domain.user.service;


import com.ssafy.trip.domain.review.dto.ReviewData;
import com.ssafy.trip.domain.review.entity.Comment.SimpleComment;
import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.entity.User;

import java.util.List;

import com.ssafy.trip.domain.user.dto.UserData.Password;
import com.ssafy.trip.domain.user.dto.UserData.Update;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    List<User> findAll();

    void updateIsLocked(Long userId);

    void update(Update update, MultipartFile profileImage, User user);

    void updatePassword(Password password, User user);

    void delete(User user);

    List<SimpleComment> commentsByUserId(Long userId);

    List<UserData.SimpleReview> getReviewsById(Long userId);

    List<UserData.SimpleReview> getLikedReviewsById(Long userId);

    UserData.SimpleProfile findById(Long userId);
}
