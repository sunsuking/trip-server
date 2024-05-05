package com.ssafy.trip.domain.auth.handler;

import com.ssafy.trip.core.properties.AuthProperties;
import com.ssafy.trip.domain.auth.entity.UserPrincipal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final AuthProperties authProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        // TODO: principal 을 이용하여 accessToken 과 refreshToken 을 발급받아야 한다.

        String redirectUrl = UriComponentsBuilder.fromUriString("http://localhost:3000")
                .queryParam("access_token", "")
                .queryParam("refresh_token", "")
                .toUriString();

        Cookie cookie = createCookie("refreshToken");
        response.addCookie(cookie);

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private Cookie createCookie(String refreshToken) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge((int) (authProperties.getRefreshTokenExpiry() / 1000));
        refreshTokenCookie.setPath("/");
        return refreshTokenCookie;
    }
}
