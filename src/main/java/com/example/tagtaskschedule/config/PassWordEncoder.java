package com.example.tagtaskschedule.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * 비밀번호 암호화 및 검증을 위한 클래스
 */
@Component
public class PassWordEncoder {

    /**
     * 평문 비밀번호를 Bcrypt로 해싱합니다.
     *
     * @param rawPassword 사용자 입력 비밀번호
     * @return 암호화된 해시 문자열
     */
    public String encode(String rawPassword) {
        return BCrypt.withDefaults()
                .hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    /**
     * 입력 비밀번호와 해시된 비밀번호를 비교합니다.
     *
     * @param rawPassword 사용자 입력 비밀번호
     * @param encodePassword 저장된 암호화된 비밀번호
     * @return 일치 여부
     */
    public boolean isPassword(String rawPassword, String encodePassword) {
        return BCrypt.verifyer()
                .verify(rawPassword.toCharArray(), encodePassword)
                .verified;
    }
}
