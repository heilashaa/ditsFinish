package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findByName(String name);

    //Y
    @Query("Select name From Test Where topic.name = ?1")
    List<String> findTestsByTopic(String name);
}
