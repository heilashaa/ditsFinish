package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.Question;
import com.dev_incubator.dits.persistence.entity.Test;
import com.dev_incubator.dits.persistence.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findAllByTopic(Topic topic);

    //Y
    List<Test> findAllTestByTopicId(Long id);

    List<Question> getTestsByTopicName(String topic);

    @Query("select questions from Test where name = ?1")
    List<Question> getQuestionsByTestName(String name);
}
