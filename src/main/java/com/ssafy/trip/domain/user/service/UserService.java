package com.ssafy.trip.domain.user.service;

import com.ssafy.trip.domain.user.dto.UserData.Profile;
import com.ssafy.trip.domain.user.entity.User;

public interface UserService {
    Profile convertToProfile(User user);
}
