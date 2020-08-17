package com.dev_incubator.dits.service.interfaces;

import com.dev_incubator.dits.persistence.entity.Question;
import com.dev_incubator.dits.persistence.entity.Test;
import com.dev_incubator.dits.service.dto.TestDto;

import java.util.List;

public interface TestService {

    List<TestDto> getAllTestInformation();

    //Y
    List<Test> findAll();

    List<Test> findAllTestByTopicId(Long id);

    List<Question> getQuestionsByTestName(String topic);
}
