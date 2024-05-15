package com.ssafy.trip.domain.user.service;


import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.dto.UserData.Password;
import com.ssafy.trip.domain.user.dto.UserData.Update;
import com.ssafy.trip.domain.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void update(Update update, MultipartFile profileImage, User user);

    void updatePassword(Password password, User user);

    void delete(User user);
}
