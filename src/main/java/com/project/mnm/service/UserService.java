package com.project.mnm.service;

import com.project.mnm.domain.User;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Long signUpUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new Exception("이미 존재하는 회원입니다.");

        user.setType(false);
        user.setUserMatching(true);
        user.setCreateAt(new Timestamp(System.currentTimeMillis()));

        userRepository.save(user);

        return user.getId();
    }
    
    public User loginUser(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        
        if (user == null) 
            throw new Exception("존재하지 않는 회원입니다.");
        
        if (!user.getPassword().equals(password))
            throw new Exception("비밀번호가 틀립니다.");
        
        return user;
    }

//    public List<User> findUsers(){
//        return userRepository.findAll();
//    }
}
