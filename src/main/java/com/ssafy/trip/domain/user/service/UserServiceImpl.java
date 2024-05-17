package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.core.service.S3UploadService;
import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.dto.UserData.Password;
import com.ssafy.trip.domain.user.dto.UserData.Update;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import java.util.List;

import static com.ssafy.trip.domain.user.dto.UserData.*;

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
        if(Objects.nonNull(profileImage)){
            CompletableFuture<String> future = s3UploadService.upload(profileImage);
            imageSrc = future.join();
        }
        if(update.isDefaultImage()){
            imageSrc = User.DEFAULT_IMAGE;
        }
        user.updateProfile(update.getNickname(),update.getCityCode(),update.getTownCode(),imageSrc);
        userMapper.update(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findByKeyword(String keyword) {
        return userMapper.findByKeyword(keyword);
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
    public void drop(Long userId) {
        userMapper.delete(userId);
    }

}
