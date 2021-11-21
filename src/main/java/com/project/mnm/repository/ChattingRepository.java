package com.project.mnm.repository;

import com.project.mnm.domain.Chatting;
import com.project.mnm.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChattingRepository extends JpaRepository<Chatting, Long> {
    List<Chatting> findByChattingRoom(ChattingRoom chattingRoom);
    List<Chatting> findByChattingRoomOrderBySendAtDesc(ChattingRoom chattingRoom);
}
