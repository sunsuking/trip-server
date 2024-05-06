package com.ssafy.trip.domain.user.dto;

import com.ssafy.trip.domain.user.entity.RoleType;
import com.ssafy.trip.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

public class UserData {

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
                    .id(user.getId())
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
