package com.ssafy.trip.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trip.domain.user.entity.RoleType;
import com.ssafy.trip.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

public class UserData {
    @Data
    public static class LoginUser {
        @JsonProperty("isLogin")
        private boolean isLogin;
        private Profile profile;

        public static LoginUser authenticated(User user) {
            LoginUser loginUser = new LoginUser();
            loginUser.setLogin(true);
            loginUser.setProfile(Profile.of(user));
            return loginUser;
        }

        public static LoginUser unauthenticated() {
            LoginUser loginUser = new LoginUser();
            loginUser.setLogin(false);
            return loginUser;
        }
    }

    @Data
    @Builder
    public static class Profile {
        private Long id;
        private String username;
        private String email;
        private String nickname;
        private String profileImage;
        private RoleType roleType;
        private boolean isEmailVerified;

        public static Profile of(User user) {
            return Profile.builder()
                    .id(user.getUserId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .profileImage(user.getProfileImage())
                    .roleType(user.getRoleType())
                    .isEmailVerified(user.isEmailVerified())
                    .build();
        }
    }
}
