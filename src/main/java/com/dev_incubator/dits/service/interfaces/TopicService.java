package com.dev_incubator.dits.service.interfaces;

import com.dev_incubator.dits.persistence.entity.Topic;
import com.dev_incubator.dits.service.dto.TopicDto;
import com.dev_incubator.dits.service.dto.TopicWithTestsDto;

import java.util.List;

public interface TopicService {

    List<TopicDto> getAllTopics();

    List<TopicWithTestsDto> getAllTopicsWithTests();

    TopicDto getTopicById(Long id);

    void deleteTopicById(Long id);

    boolean saveTopic(TopicDto topicDto);

    //Y
    List<Topic> findAll();

    List<String> findTestsByTopic(String name);
}
