package com.ssafy.trip.domain.auth.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendSignUpEmail(String to, String nickname, String code) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("BrandU 회원가입 인증 메일입니다.");
        helper.setTo(to);


        String confirmURI = createURI("sign-up", code, to);

        Context context = new Context();
        context.setVariable("confirmURI", confirmURI);
        context.setVariable("nickname", nickname);

        String html = templateEngine.process("mail/confirm_member_account_mail", context);
        helper.setText(html, true);

        sendEmail(message, to);
    }

    public void sendFindPasswordEmail(String to, String code) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("BrandU 비밀번호 찾기 메일입니다.");
        helper.setTo(to);

        Context context = new Context();
        context.setVariable("code", code);

        String html = templateEngine.process("mail/find_member_account_mail", context);
        helper.setText(html, true);

        sendEmail(message, to);
    }

    private void sendEmail(MimeMessage message, String to) {
        new Thread(() -> {
            try {
                javaMailSender.send(message);
                log.info("{} 으로 이메일 전송에 성공했습니다.", to);
            } catch (Exception e) {
                log.error("이메일 전송에 실패했습니다. 다시 시도해주세요.", e);
            }
        }).start();
    }

    private String createURI(String type, String code, String email) {
        return UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/auth/confirm")
                .queryParam("type", type)
                .queryParam("code", code)
                .queryParam("email", email)
                .toUriString();
    }
}
