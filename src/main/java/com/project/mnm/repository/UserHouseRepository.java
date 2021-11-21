package com.project.mnm.repository;

import com.project.mnm.domain.House;
import com.project.mnm.domain.User;
import com.project.mnm.domain.UserHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHouseRepository extends JpaRepository<UserHouse, Long> {
    UserHouse findByUserAndHouse(User user, House house);
    List<UserHouse> findAllByUser(User user);
    List<UserHouse> findAllByHouse(House house);
}
