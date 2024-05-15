package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.core.service.S3UploadService;
import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.dto.UserData.Update;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final S3UploadService s3UploadService;


    @Override
    public void update(Update update, MultipartFile profileImage, User user) {
        String imageSrc = user.getProfileImage();
        if(Objects.nonNull(profileImage)){
            CompletableFuture<String> future = s3UploadService.upload(profileImage);
            imageSrc = future.join();
        }
        user.updateProfile(update.getNickname(),imageSrc);
        userMapper.update(user);
    }
}
