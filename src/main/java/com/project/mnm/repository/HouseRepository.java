package com.project.mnm.repository;

import com.project.mnm.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    @Query(value = "select h from House h where h.captainId = ?1")
    List<House> findHousesByUserId(long uid);

}
