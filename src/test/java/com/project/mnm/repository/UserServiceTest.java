package com.project.mnm.repository;

import com.project.mnm.domain.User;
import com.project.mnm.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    User user;

    @BeforeEach()
    public void initUser() {
        this.user = new User();
        this.user.setEmail("ririhan217@test.com");
        this.user.setPassword("blackpink0808");
    }

    @Test
    public void signUp() throws Exception {
        userService.signUpUser(user);
    }
}
