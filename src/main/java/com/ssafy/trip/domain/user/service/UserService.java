package com.ssafy.trip.domain.user.service;


import com.ssafy.trip.domain.review.entity.Comment.SimpleComment;
import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.entity.SimpleUser;
import com.ssafy.trip.domain.user.entity.User;

import java.util.List;

import com.ssafy.trip.domain.user.dto.UserData.Password;
import com.ssafy.trip.domain.user.dto.UserData.Update;
import com.ssafy.trip.domain.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<User> findAll();

    UserData.Profile getProfile(Long userId);

    // 키워드로 회원 조회
    List<User> findByKeyword(String keyword);

    void updateIsLocked(Long userId);

    void update(Update update, MultipartFile profileImage, User user);

    void updatePassword(Password password, User user);

    void delete(User user);

    // 회원 탈퇴
    void drop(Long userId);

    List<SimpleComment> commentsByUserId(Long userId);

    List<UserData.SimpleReview> getReviewsById(Long userId);

    List<UserData.SimpleReview> getLikedReviewsById(Long userId);

    UserData.SimpleProfile findById(Long userId,Long currentId);

    boolean isFollow(Long followeeId, Long followerId);

    void followUser(Long followeeId, Long followerId);

    void unFollowUser(Long followeeId, Long followerId);

    List<SimpleUser> getFollowing(Long userId, Long currentId);

    List<SimpleUser> getFollowers(Long userId, Long currentId);

    int getFollowerCount(Long userId);

    int getFollowingCount(Long userId);


}
