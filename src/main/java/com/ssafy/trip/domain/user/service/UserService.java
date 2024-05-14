package com.ssafy.trip.domain.user.service;


import com.ssafy.trip.domain.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
