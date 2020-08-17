package com.dev_incubator.dits.service.dto.mapper;

import com.dev_incubator.dits.persistence.entity.Topic;
import com.dev_incubator.dits.persistence.entity.TopicStatistic;
import com.dev_incubator.dits.service.dto.TopicDto;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class TopicMapper {

    public TopicDto toDto(final Topic topic, TopicStatistic topicStatistic) {
        TopicDto dto = new TopicDto();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());
        dto.setTestAmount(topic.getTests().size());
        dto.setAmountPassedTests(topicStatistic.getAmountPass());
        return dto;
    }

    public Topic fromDto(final TopicDto dto) {
        Topic topic = new Topic();
        if (nonNull(dto.getId())) {
            topic.setId(dto.getId());
        }
        topic.setName(dto.getName());
        topic.setDescription(dto.getDescription());
        return topic;
    }
}
