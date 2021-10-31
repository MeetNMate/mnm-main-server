package com.project.mnm.service;

import com.project.mnm.domain.User;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long join(User user){
        userRepository.findByEmail(user.getEmail()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        userRepository.save(user);
        return user.getId();
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }
}
