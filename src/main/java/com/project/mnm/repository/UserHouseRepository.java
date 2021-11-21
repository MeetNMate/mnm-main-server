package com.project.mnm.repository;

import com.project.mnm.domain.House;
import com.project.mnm.domain.User;
import com.project.mnm.domain.UserHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHouseRepository extends JpaRepository<UserHouse, Long> {
    UserHouse findByUserAndHouse(User user, House house);
    List<UserHouse> findAllByUser(User user);
}
