package com.dev_incubator.dits.service.interfaces;

import com.dev_incubator.dits.persistence.entity.*;
import com.dev_incubator.dits.service.dto.UserDto;

import java.util.List;

public interface StatisticService {

    List<TestStatistic> getStatisticByTests();

    List<QuestionStatistic> getStatisticByQuestions();

    List<UserStatistic> getStatisticByUsers();

    List<PersonalStatistic> getPersonalStatistic(UserDto user);

    List<TopicStatistic> getStatisticByTopics();
}
