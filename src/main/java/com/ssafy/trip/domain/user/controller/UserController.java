package com.ssafy.trip.domain.user.controller;

import com.ssafy.trip.core.annotation.CurrentUser;
import com.ssafy.trip.core.response.SuccessResponse;
import com.ssafy.trip.domain.user.dto.UserData.Profile;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<Profile> me(@CurrentUser User user) {
        return SuccessResponse.of(userService.convertToProfile(user));
    }
}
