package com.project.mnm.repository;

import com.project.mnm.domain.ResidenceInfo;
import com.project.mnm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidenceInfoRepository extends JpaRepository<ResidenceInfo, Long>  {
    Optional<ResidenceInfo> findByUser(User user);
}
