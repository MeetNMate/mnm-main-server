package com.project.mnm.repository;

import com.project.mnm.domain.Chatting;
import com.project.mnm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChattingRepository extends JpaRepository<Chatting, Long> {
    Optional<Chatting> findByUser(User user);
//    Optional<ChattingRoom> findByChattingRoom(ChattingRoom chattingRoom);
}
