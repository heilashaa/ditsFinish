package com.dev_incubator.dits.service.interfaces;

import com.dev_incubator.dits.persistence.entity.Answer;
import com.dev_incubator.dits.persistence.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAll();

    List<Answer> getAnswersByQuestionId(Long id);
}