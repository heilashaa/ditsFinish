package com.dev_incubator.dits.service.interfaces;

import com.dev_incubator.dits.persistence.entity.Answer;

public interface AnswerService {
    Answer getCorrectByDescription(Long id);
}
