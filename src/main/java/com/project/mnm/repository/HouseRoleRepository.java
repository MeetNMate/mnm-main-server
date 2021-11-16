package com.project.mnm.repository;

import com.project.mnm.domain.House;
import com.project.mnm.domain.HouseRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRoleRepository extends JpaRepository<HouseRole, Long> {
    List<HouseRole> findByHouse(Optional<House> house);
}
