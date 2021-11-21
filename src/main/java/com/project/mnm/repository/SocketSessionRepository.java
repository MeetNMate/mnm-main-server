package com.project.mnm.repository;

import com.project.mnm.domain.SocketSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocketSessionRepository extends JpaRepository<SocketSession, String> {
}
