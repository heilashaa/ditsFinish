package com.dev_incubator.dits.service;

import com.dev_incubator.dits.persistence.entity.Question;
import com.dev_incubator.dits.persistence.entity.Test;
import com.dev_incubator.dits.persistence.repository.TestRepository;
import com.dev_incubator.dits.service.dto.TestDto;
import com.dev_incubator.dits.service.dto.mapper.TestMapper;
import com.dev_incubator.dits.service.interfaces.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    private final TestMapper testMapper;

    @Override
    public List<TestDto> getAllTestInformation() {

        List<TestDto> tests = testRepository.findAll().stream().map(testMapper::toDto).collect(Collectors.toList());

        return tests;

    }
    //Y
    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public List<Test> findAllTestByTopicId(Long id) {
        return testRepository.findAllTestByTopicId(id);
    }

    @Override
    public List<Question> getQuestionsByTestName(String name) {

        return testRepository.getQuestionsByTestName(name);
    }
}
