package com.project.mnm.repository;

import com.project.mnm.domain.Evaluation;
import com.project.mnm.domain.House;
import com.project.mnm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findAllByAppraisee(User appraisee);

    List<Evaluation> findAllByHouseAndAppraiser(House house, User appraiser);
}
