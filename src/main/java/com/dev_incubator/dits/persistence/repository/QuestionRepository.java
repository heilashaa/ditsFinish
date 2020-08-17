package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.Answer;
import com.dev_incubator.dits.persistence.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    //Y
    @Query("from Answer where question.id = ?1")
    List<Answer> getAnswersByQuestionId(Long id);
}
