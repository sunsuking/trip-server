package com.ssafy.trip.domain.auth.service;

import com.ssafy.trip.domain.auth.dto.AuthData.*;
import com.ssafy.trip.domain.user.entity.User;

import static com.ssafy.trip.domain.auth.dto.AuthData.*;

public interface AuthService {
    JwtToken signIn(SignIn signIn);

    void signUp(SignUp signUp);

    void signOut(User user, String refreshToken);

    boolean confirm(Confirm confirm);

    void findPassword(String email);

    void resendEmail(String email, String type);

    JwtToken refresh(String refreshToken);
}
