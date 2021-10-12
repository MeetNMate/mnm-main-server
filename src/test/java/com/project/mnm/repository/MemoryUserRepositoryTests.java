package com.project.mnm.repository;

import com.project.mnm.domain.User;
import com.project.mnm.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemoryUserRepositoryTests {
    MemoryUserRepository repository = new MemoryUserRepository();

    @Qualifier("jpaUserRepository")
    @Autowired UserRepository userRepository;
    @Autowired
    UserService userService;

    @AfterEach
    public void afterEach (){
        repository.clearStore();
    }

    @Test
    public void save(){
        User user = new User();
        user.setEmail("songe");

        repository.save(user);
        User result = repository.findById(user.getId()).get();
        assertThat(user).isEqualTo(result);
    }

    @Test
    public void findByEmail(){
        User user1 =new User();
        User user2 =new User();
        user1.setEmail("song");
        user2.setEmail("kim");
        repository.save(user1);
        repository.save(user2);

        User result = repository.findByEmail("song").get();

        assertThat(result).isEqualTo(user1);
    }

    @Test
    public void jpatest(){
        User user1 =new User();
        User user2 =new User();
        user1.setEmail("song");
        user2.setEmail("kim");

        Long saveID = userService.join(user1);
    }
}
