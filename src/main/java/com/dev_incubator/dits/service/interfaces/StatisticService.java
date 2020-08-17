package com.dev_incubator.dits.service.interfaces;

import com.dev_incubator.dits.persistence.entity.*;
import com.dev_incubator.dits.service.dto.UserDto;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StatisticService {

    List<TestStatistic> getStatisticByTests();

    List<QuestionStatistic> getStatisticByQuestions();

    List<UserStatistic> getStatisticByUsers();

    List<PersonalStatistic> getPersonalStatistic(UserDto user);

    List<TopicStatistic> getStatisticByTopics();

    //Y
    Map<String, Statistic> statList = new HashMap<>();

    List<Statistic> findAllStatistics();

    void saveMapOfStat(Map<String, Statistic> map, Timestamp endTest);

    void changeCorrectValue(Timestamp date, User user, Question question, boolean correct);

    List<Statistic> findAllByDate(Timestamp date);

    List<Statistic> getAllByDate(Timestamp date);
}
