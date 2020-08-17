package com.dev_incubator.dits.service;

import com.dev_incubator.dits.exception.TopicNotFoundException;
import com.dev_incubator.dits.persistence.entity.Topic;
import com.dev_incubator.dits.persistence.entity.TopicStatistic;
import com.dev_incubator.dits.persistence.repository.TestRepository;
import com.dev_incubator.dits.persistence.repository.TopicRepository;
import com.dev_incubator.dits.persistence.repository.TopicStatisticRepository;
import com.dev_incubator.dits.service.dto.TopicDto;
import com.dev_incubator.dits.service.dto.TopicWithTestsDto;
import com.dev_incubator.dits.service.dto.mapper.TestMapper;
import com.dev_incubator.dits.service.dto.mapper.TopicMapper;
import com.dev_incubator.dits.service.dto.mapper.TopicWithTestsMapper;
import com.dev_incubator.dits.service.interfaces.TopicService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Getter
@Setter
@Transactional
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    private final TopicStatisticRepository topicStatisticRepository;

    private final TestRepository testRepository;

    private final TopicMapper topicMapper;

    private final TopicWithTestsMapper topicWithTestsMapper;

    private final TestMapper testMapper;

    @Override
    public List<TopicDto> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(t -> topicMapper.toDto(t, topicStatisticRepository.findByTopic(t)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TopicWithTestsDto> getAllTopicsWithTests() {
        return topicRepository.findAll().stream()
                .map(t -> topicWithTestsMapper.toDto(
                        t,
                        topicStatisticRepository.findByTopic(t),
                        testRepository.findAllByTopic(t))
                )
                .collect(Collectors.toList());
    }

    @Override
    public TopicDto getTopicById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException("topic.not.found"));
        TopicStatistic topicStatistic = topicStatisticRepository.findByTopic(topic);
        return topicMapper.toDto(topic, topicStatistic);
    }

    @Override
    public void deleteTopicById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException("topic.not.found"));
        topicRepository.delete(topic);
    }

    @Override
    public boolean saveTopic(TopicDto topicDto) {
        Topic topicFromDb = topicRepository.findByName(topicDto.getName());
        //edit topic
        if (nonNull(topicDto.getId())) {
            if (nonNull(topicFromDb) && !topicFromDb.getId().equals(topicDto.getId())) {
                return false;
            }
            topicRepository.save(topicMapper.fromDto(topicDto));
            return true;
        }
        //create topic
        if (nonNull(topicFromDb)) {
            return false;
        }
        topicRepository.save(topicMapper.fromDto(topicDto));
        return true;
    }
}
