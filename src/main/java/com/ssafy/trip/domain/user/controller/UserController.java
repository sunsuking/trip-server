package com.ssafy.trip.domain.user.controller;

import com.ssafy.trip.core.annotation.CurrentUser;
import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.dto.UserData.Update;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
 
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<UserData.LoginUser> me(@CurrentUser User user) {
        if (user == null) {
            return SuccessResponse.of(UserData.LoginUser.unauthenticated());
        }
        return SuccessResponse.of(UserData.LoginUser.authenticated(user));
    }

    @PatchMapping("/me")
    public SuccessResponse<Void> update(
            Update update,
            @RequestParam(value = "profileImage",required = false) MultipartFile  profileImage,
            @CurrentUser User user
    ) {
        userService.update(update,profileImage,user);
        return SuccessResponse.empty();
    }
}
