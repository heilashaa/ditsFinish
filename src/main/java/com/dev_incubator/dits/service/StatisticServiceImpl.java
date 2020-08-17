package com.dev_incubator.dits.service;

import com.dev_incubator.dits.exception.UserHasNoStatisticException;
import com.dev_incubator.dits.persistence.entity.*;
import com.dev_incubator.dits.persistence.repository.*;
import com.dev_incubator.dits.service.dto.UserDto;
import com.dev_incubator.dits.service.dto.mapper.UserMapper;
import com.dev_incubator.dits.service.interfaces.StatisticService;
import com.dev_incubator.dits.util.MessageSourceFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final TestStatisticRepository testStatisticRepository;

    private final QuestionStatisticRepository questionStatisticRepository;

    private final UserStatisticRepository userStatisticRepository;

    private final PersonalStatisticRepository personalStatisticRepository;

    private final TopicStatisticRepository topicStatisticRepository;

    private final RoleRepository roleRepository;

    private final MessageSourceFacade messageSource;

    private final UserMapper userMapper;

    private final StatisticRepository statisticRepository;

    @Override
    public List<TestStatistic> getStatisticByTests() {
        return testStatisticRepository.findAll();
    }

    @Override
    public List<QuestionStatistic> getStatisticByQuestions() {
        return questionStatisticRepository.findAll();
    }

    @Override
    public List<UserStatistic> getStatisticByUsers() {
        return userStatisticRepository.findAll();
    }

    @Override
    public List<PersonalStatistic> getPersonalStatistic(UserDto user) {
        return personalStatisticRepository.findAllByUser(userMapper.fromDto(user, roleRepository.findAll()))
                .orElseThrow(() -> new UserHasNoStatisticException(messageSource.getMessage("user.has.no.statistic")));
    }

    @Override
    public List<TopicStatistic> getStatisticByTopics() {
        return topicStatisticRepository.findAll();
    }

    //Y
    @Override
    public List<Statistic> findAllStatistics() {
        return statisticRepository.findAll();
    }

    @Override
    public void saveMapOfStat(Map<String, Statistic> map, Timestamp endTest) {
        for (Statistic statistic : map.values()) {
            statistic.setDate(endTest);
            statisticRepository.save(statistic);
        }
    }

    @Override
    public void changeCorrectValue(Timestamp date, User user, Question question, boolean correct) {
        statisticRepository.changeCorrectValue(date, user, question,
                correct);
    }

    @Override
    public List<Statistic> findAllByDate(Timestamp date) {
        return statisticRepository.findAllByDate(date);
    }

    @Override
    public List<Statistic> getAllByDate(Timestamp date) {
        return statisticRepository.getAllByDate(date);
    }
}
