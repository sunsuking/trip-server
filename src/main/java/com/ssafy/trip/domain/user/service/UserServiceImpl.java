package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.dto.UserData;
import com.ssafy.trip.domain.user.entity.User;
import com.ssafy.trip.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public UserData.Profile convertToProfile(User user) {
        return UserData.Profile.of(user);
    }
}
