package com.dev_incubator.dits.service;

import com.dev_incubator.dits.persistence.entity.Answer;
import com.dev_incubator.dits.persistence.repository.AnswerRepository;
import com.dev_incubator.dits.service.interfaces.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Answer getCorrectByDescription(Long id) {
        return answerRepository.getCorrectByDescription(id);
    }
}
