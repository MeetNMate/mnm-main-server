package com.project.mnm.service.user;

import com.project.mnm.domain.User;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findOneUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
    }

    public void removeAllUsers() {
        userRepository.deleteAll();
    }

    public void removeOneUserByEmail(String email) {
        User member = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        userRepository.delete(member);
    }

    public void removeOneUserById(Long id) {
        User member = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        userRepository.delete(member);
    }
}
