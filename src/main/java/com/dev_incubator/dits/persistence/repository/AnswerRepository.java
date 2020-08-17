package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    //Y
    @Query("from Answer where id = ?1")
    Answer getCorrectByDescription(Long id);
}
