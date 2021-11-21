package com.project.mnm.repository;

import com.project.mnm.domain.House;
import com.project.mnm.domain.HouseRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRuleRepository extends JpaRepository<HouseRule, Long> {
    List<HouseRule> findByHouse(Optional<House> house);
}
