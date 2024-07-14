package com.ssafy11.woojoobook.infra.mail;

public interface EmailService {
    void sendEmail(String verificationCode, String to);
}
