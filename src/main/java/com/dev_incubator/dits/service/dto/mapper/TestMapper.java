package com.dev_incubator.dits.service.dto.mapper;

import com.dev_incubator.dits.persistence.entity.Test;
import com.dev_incubator.dits.service.dto.TestDto;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {

    public TestDto toDto(final Test test) {
        TestDto dto = new TestDto();
        dto.setId(test.getId());
        dto.setName(test.getName());
        dto.setDescription(test.getDescription());
        dto.setTopic(test.getTopic());
        dto.setQuestions(test.getQuestions());
        return dto;
    }
}
