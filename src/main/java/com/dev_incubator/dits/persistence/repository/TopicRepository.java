package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByName(String name);
}
