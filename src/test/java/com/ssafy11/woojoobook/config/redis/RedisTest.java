package com.ssafy11.woojoobook.config.redis;

import com.ssafy11.woojoobook.member.MemberService;
import com.ssafy11.woojoobook.member.MemberVerification;
import com.ssafy11.woojoobook.member.MemberVerificationRepository;
import com.ssafy11.woojoobook.member.VerificationCodeUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Import(TestConfig.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private MemberVerificationRepository memberVerificationRepository;

    @Autowired
    private MemberService memberService;

    @DisplayName("인증할 회원 정보를 redis에 저장한다")
    @Test
    void createMemberVerification(){
        // given
        String email = "test@email.com";

        // when
        this.memberService.createVerificationMember(email);

        // then
        Optional<MemberVerification> byId = memberVerificationRepository.findById(email);
        assertNotNull(byId);
        assertEquals(email, byId.get().getEmail());
    }

    @DisplayName("올바른 인증번호라면 회원의 인증여부를 true 로 업데이트")
    @Test
    void updateMemberVerification(){
        // given
        String email = "test@email.com";
        String code = VerificationCodeUtil.generate();
        MemberVerification memberVerification = MemberVerification.builder()
                .email(email)
                .verificationCode(code)
                .isVerified(false)
                .build();

        memberVerificationRepository.save(memberVerification);

        // when
        boolean isVerified = memberService.verifyMember(email, code);

        // then
        assertTrue(isVerified);
        Optional<MemberVerification> byId = memberVerificationRepository.findById(memberVerification.getEmail());
        assertNotNull(byId);
        assertEquals(memberVerification.getEmail(), byId.get().getEmail());
        assertTrue(byId.get().isVerified());
    }
}
