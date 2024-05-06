package com.ssafy.trip.domain.auth.service;

import com.ssafy.trip.core.exception.CustomException;
import com.ssafy.trip.core.exception.ErrorCode;
import com.ssafy.trip.domain.auth.repository.AuthCacheRepository;
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
    private final AuthCacheRepository authCacheRepository;

    public void sendSignUpEmail(String to, String nickname) {
        String code = generateCode();
        try {
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
            authCacheRepository.saveConfirm(to, code);
        } catch (MessagingException e) {
            log.error("이메일 전송에 실패했습니다. 다시 시도해주세요.", e);
            throw new CustomException(ErrorCode.EMAIL_SEND_FAILED);
        }
    }

    public void sendFindPasswordEmail(String to) {
        String code = generateCode();
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setSubject("BrandU 비밀번호 찾기 메일입니다.");
            helper.setTo(to);

            Context context = new Context();
            context.setVariable("code", code);

            String html = templateEngine.process("mail/find_member_account_mail", context);
            helper.setText(html, true);

            sendEmail(message, to);
            authCacheRepository.saveFindPassword(to, code);
        } catch (MessagingException e) {
            log.error("이메일 전송에 실패했습니다. 다시 시도해주세요.", e);
            throw new CustomException(ErrorCode.EMAIL_SEND_FAILED);
        }
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
        return UriComponentsBuilder.fromUriString("http://localhost:5173/confirm/email")
                .queryParam("type", type)
                .queryParam("code", code)
                .queryParam("email", email)
                .toUriString();
    }

    private String generateCode() {
        return String.valueOf((int) (Math.random() * 899999) + 100000);
    }
}
