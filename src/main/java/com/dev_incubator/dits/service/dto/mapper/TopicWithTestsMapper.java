package com.dev_incubator.dits.service.dto.mapper;

import com.dev_incubator.dits.persistence.entity.Test;
import com.dev_incubator.dits.persistence.entity.Topic;
import com.dev_incubator.dits.persistence.entity.TopicStatistic;
import com.dev_incubator.dits.service.dto.TopicWithTestsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TopicWithTestsMapper {

    private final TestMapper testMapper;

    public TopicWithTestsDto toDto(final Topic topic, TopicStatistic topicStatistic, List<Test> tests) {
        TopicWithTestsDto dto = new TopicWithTestsDto();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());
        dto.setTestAmount(topic.getTests().size());
        dto.setAmountPassedTests(topicStatistic.getAmountPass());
        dto.setTests(tests.stream().map(testMapper::toDto).collect(Collectors.toList()));
        return dto;
    }
}
