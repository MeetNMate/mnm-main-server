package com.project.mnm.repository;

import com.project.mnm.domain.ChattingRoom;
import com.project.mnm.domain.User;
import com.project.mnm.domain.UserChatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserChattingRepository extends JpaRepository<UserChatting, Long> {
    Optional<UserChatting> findByUserAndChattingRoom(User user, ChattingRoom chattingRoom);
    List<UserChatting> findByUser(User user);
    List<UserChatting> findByChattingRoom(ChattingRoom chattingRoom);
}
