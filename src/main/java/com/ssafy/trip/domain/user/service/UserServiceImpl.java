package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.core.exception.CustomException;
import com.ssafy.trip.core.exception.ErrorCode;
import com.ssafy.trip.domain.review.dto.ReviewData.SimpleReview;
import com.ssafy.trip.domain.review.entity.Comment.SimpleComment;
import com.ssafy.trip.domain.review.entity.ReviewWithUser;
import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.entity.SimpleUser;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.core.service.S3UploadService;
import com.ssafy.trip.domain.user.dto.UserData.Password;
import com.ssafy.trip.domain.user.dto.UserData.Update;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final S3UploadService s3UploadService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void update(Update update, MultipartFile profileImage, User user) {
        String imageSrc = user.getProfileImage();
        if (Objects.nonNull(profileImage)) {
            CompletableFuture<String> future = s3UploadService.upload(profileImage);
            imageSrc = future.join();
        }
        if (update.isDefaultImage()) {
            imageSrc = User.DEFAULT_IMAGE;
        }
        user.updateProfile(update.getNickname(), update.getCityCode(), update.getTownCode(), imageSrc);
        userMapper.update(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void updateIsLocked(Long userId) {
        userMapper.updateIsLocked(userId);
    }

    @Override
    public void updatePassword(Password password, User user) {
        user.resetPassword(passwordEncoder.encode(password.getPassword()));
        userMapper.update(user);
    }

    @Override
    public void delete(User user) {
        userMapper.delete(user.getUserId());
    }

    @Override
    public List<SimpleComment> commentsByUserId(Long userId) {
        return userMapper.commentsByUserId(userId);
    }

    @Override
    public List<UserData.SimpleReview> getReviewsById(Long userId) {
        return userMapper.getReviewsById(userId);
    }

    @Override
    public List<UserData.SimpleReview> getLikedReviewsById(Long userId) {
        return userMapper.getLikedReviewsById(userId);
    }

    @Override
    public UserData.SimpleProfile findById(Long userId) {
        return userMapper.findById(userId);
    }

    @Override
    public boolean isFollow(Long followeeId, Long followerId) {
        return userMapper.isFollow(followeeId,followerId);
    }

    @Override
    public void followUser(Long followeeId, Long followerId) {
        userMapper.follow(followeeId, followerId);
    }

    @Override
    public void unFollowUser(Long followeeId, Long followerId) {
        userMapper.unFollow(followeeId, followerId);
    }

    @Override
    public List<SimpleUser> getFollows(Long userId) {
        return userMapper.getFollows(userId);
    }

    @Override
    public List<SimpleUser> getFollowers(Long userId) {
        return userMapper.getFollowers(userId);
    }

    @Override
    public int getFollowCount(Long userId) {
        return userMapper.getFollowCount(userId);
    }

    @Override
    public int getFollowingCount(Long userId) {
        return userMapper.getFollowingCount(userId);
    }
}
