package com.ssafy11.woojoobook.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberVerificationRepository memberVerificationRepository;

    public boolean verifyMember(String email, String code) {
        MemberVerification memberVerification = this.memberVerificationRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 email"));
        memberVerification.verify(code);

        MemberVerification save = this.memberVerificationRepository.save(memberVerification);

        return save.isVerified();
    }

    public void createVerificationMember(String email){
        MemberVerification memberVerification = MemberVerification.builder()
                .email(email)
                .verificationCode(VerificationCodeUtil.generate())
                .isVerified(false)
                .build();

        this.memberVerificationRepository.save(memberVerification);
    }
}
