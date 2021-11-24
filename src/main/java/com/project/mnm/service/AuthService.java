package com.project.mnm.service;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.domain.User;
import com.project.mnm.dto.AuthLoginResponseDto;
import com.project.mnm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public User joinUser(User user) throws Exception {
        if (!userRepository.findByEmail(user.getEmail()).isEmpty())
            throw new Exception("이미 존재하는 회원입니다.");

        if (user.getPassword() == null || user.getPassword().equals(""))
            throw new Exception("비밀번호를 입력해주세요.");

        return userRepository.save(User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .type(false)
                .useMatching(true)
                .createAt(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    public AuthLoginResponseDto loginUser(User user) throws Exception {
        User member = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (member.getRemoveAt() != null) {
            throw new Exception("탈퇴한 사용자입니다.");
        }

        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());

        AuthLoginResponseDto authLoginResponseDto = new AuthLoginResponseDto();
        authLoginResponseDto.setUid(member.getId());
        authLoginResponseDto.setToken(token);

        return authLoginResponseDto;
    }

    public void logoutUser(User user) {

    }

    public void removeUser(User user) {
        User member = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        member.setRemoveAt(new Timestamp(System.currentTimeMillis()));

        userRepository.save(member);
    }
}
