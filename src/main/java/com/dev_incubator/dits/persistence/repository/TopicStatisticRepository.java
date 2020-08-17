package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.Topic;
import com.dev_incubator.dits.persistence.entity.TopicStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicStatisticRepository extends JpaRepository<TopicStatistic, Long> {

    TopicStatistic findByTopic(Topic topic);
}
