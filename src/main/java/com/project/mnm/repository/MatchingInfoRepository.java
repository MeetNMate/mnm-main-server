package com.project.mnm.repository;

import com.project.mnm.domain.MatchingInfo;
import com.project.mnm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchingInfoRepository extends JpaRepository<MatchingInfo, Long> {
    Optional<MatchingInfo> findByUser(User user);
}
