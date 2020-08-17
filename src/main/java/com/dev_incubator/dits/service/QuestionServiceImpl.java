package com.dev_incubator.dits.service;

import com.dev_incubator.dits.persistence.entity.Answer;
import com.dev_incubator.dits.persistence.entity.Question;
import com.dev_incubator.dits.persistence.repository.QuestionRepository;
import com.dev_incubator.dits.service.interfaces.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Long id) {
        return questionRepository.getAnswersByQuestionId(id);
    }
}
