package com.ssafy.trip.domain.user.service;


import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.entity.User;

import java.util.List;

import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.dto.UserData.Password;
import com.ssafy.trip.domain.user.dto.UserData.Update;
import com.ssafy.trip.domain.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    List<User> findAll();

    // 키워드로 회원 조회
    List<User> findByKeyword(String keyword);

    void updateIsLocked(Long userId);

    void update(Update update, MultipartFile profileImage, User user);

    void updatePassword(Password password, User user);

    void delete(User user);

    // 회원 탈퇴
    void drop(Long userId);
}
