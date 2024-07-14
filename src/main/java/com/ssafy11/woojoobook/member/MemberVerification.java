package com.ssafy11.woojoobook.member;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash(value = "memberVerification", timeToLive = 300)
public class MemberVerification implements Serializable {
    @Id
    private String email;
    private String verificationCode;
    private boolean isVerified;

    @Builder
    public MemberVerification(String email, String verificationCode, boolean isVerified) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.isVerified = isVerified;
    }

    public boolean verify(String verificationCode) {
        if(verificationCode.equals(this.verificationCode)) {
            this.isVerified = true;
            return true;
        }
        return false;
    }
}
