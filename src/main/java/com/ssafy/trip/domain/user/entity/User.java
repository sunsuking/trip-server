package com.ssafy.trip.domain.user.entity;

import com.ssafy.trip.domain.auth.attribute.OAuth2Attribute;
import com.ssafy.trip.domain.auth.dto.AuthData;
import com.ssafy.trip.domain.auth.entity.ProviderType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String nickname;
    private String profileImage;
    private ProviderType providerType;
    private RoleType roleType = RoleType.USER;
    private boolean isEmailVerified = false;
    private boolean isLocked;

    /**
     * 소셜 로그인 기반 신규 유저 생성
     *
     * @param attribute {@link OAuth2Attribute}
     * @param provider  {@link ProviderType}
     * @return {@link User}
     */
    public static User createOAuthUser(OAuth2Attribute attribute, ProviderType provider) {
        User user = new User();
        user.username = attribute.getEmail();
        user.password = UUID.randomUUID().toString();
        user.nickname = attribute.getName();
        user.profileImage = attribute.getImageUrl();
        user.email = attribute.getEmail();
        user.providerType = provider;
        user.roleType = RoleType.USER;
        user.isLocked = false;
        return user;
    }

    /**
     * 로컬 로그인 기반 신규 유저 생성
     *
     * @param signUp {@link AuthData.SignUp}
     * @return {@link User}
     */
    public static User createLocalUser(AuthData.SignUp signUp) {
        User user = new User();
        user.username = signUp.getUsername();
        user.email = signUp.getEmail();
        user.password = signUp.getPassword();
        user.providerType = ProviderType.LOCAL;
        user.roleType = RoleType.USER;
        user.isLocked = false;
        return user;
    }

    public void confirmEmail() {
        this.isEmailVerified = true;
    }

    public void resetPassword(String password) {
        this.password = password;
    }
}
