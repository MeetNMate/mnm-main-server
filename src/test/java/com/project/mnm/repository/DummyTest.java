package com.project.mnm.repository;

import com.project.mnm.domain.User;
import com.project.mnm.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.stream.IntStream;

@SpringBootTest
public class DummyTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

//    @Test
//    public void InsertDummies() {
//        IntStream.rangeClosed(6, 72).forEach(i -> {
//            User user = User.builder()
//                    .id(i)
//                    .email(i+"test@mnm.com")
//                    .password("0808")
//                    .type(false)
//                    .useMatching(true)
//                    .createAt(new Timestamp(System.currentTimeMillis()))
//                    .build();
//            userRepository.save(user);
//        });
//    }

//    @Test
//    public void DeleteDummies() {
//        IntStream.rangeClosed(140, 206).forEach(i -> {
//            userRepository.deleteById((long) i);
//        });
//    }

    @Test
    public void joinDummies() {
        IntStream.rangeClosed(8, 72).forEach(i -> {
            User user = new User();
            user.setEmail("mnmtest"+i+"@gmail.com");
            user.setPassword("blackpink0808");
            try {
                authService.joinUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
