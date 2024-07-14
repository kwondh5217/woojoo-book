package com.ssafy11.woojoobook.config.security.jwt;

import com.ssafy11.woojoobook.infra.config.security.jwt.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtProviderTest {

    private JwtProvider jwtProvider;
    private String secretKey = "secretKey";
    private long expirationTime = 1000L;

    @BeforeEach
    void setUp() {
        this.jwtProvider = new JwtProvider();
    }

    @DisplayName("유저 정보로 토큰을 정상적으로 생성")
    @Test
    void testGenerateToken() {


    }

}