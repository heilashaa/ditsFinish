package com.dev_incubator.dits.controller;

import com.dev_incubator.dits.config.security.CustomUserDetails;
import com.dev_incubator.dits.exception.TopicNotFoundException;
import com.dev_incubator.dits.persistence.entity.Answer;
import com.dev_incubator.dits.persistence.entity.Question;
import com.dev_incubator.dits.persistence.entity.Statistic;
import com.dev_incubator.dits.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class TestPageController {

    private static int counter;
    private static CustomUserDetails user;
    private static int max;
    private static Timestamp date;
    private static List<Question> questionList;

    private final UserService userService;

    private final TestService testService;

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final StatisticService statisticService;

    @GetMapping(value = "/goTest")
    public String goTest(@RequestParam String testName, ModelMap modelMap) {
        clearCounter();
        initVariables(testName);
        fillTestDB();
        if (questionList != null && questionList.size() != 0) {
            modelMap.addAttribute("questions", questionList.get(counter)
                    .getDescription());
            modelMap.addAttribute("answers", questionService.getAnswersByQuestionId
                    (questionList.get(counter).getId()));
        } else {
            throw new TopicNotFoundException("");
        }
        return "testPage";
    }

    @GetMapping(value = "/nextTestPage")
    public String nextTestPage(
            @RequestParam(value = "choosenAns", defaultValue = "-1") Long
                    id, ModelMap modelMap) {
        if (id != -1) {
            changeCorrectValue(answerService.getCorrectByDescription(id));
        }
        counter++;
        return chooseNextPage(modelMap);
    }

    private String chooseNextPage(ModelMap modelMap) {
        if (counter < max) {
            modelMap.addAttribute("questions", questionList.get(counter)
                    .getDescription());
            modelMap.addAttribute("answers", questionService.getAnswersByQuestionId
                    (questionList.get(counter).getId()));

            return "testPage";
        } else {
            return resultPageFill(modelMap);
        }
    }

    @GetMapping("/resultPage")
    public String resultPageFill(ModelMap modelMap) {
        clearCounter();
        modelMap.addAttribute("statistic",
                statisticService.getAllByDate(date));
        return "resultPage";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        clearCounter();
        return "main";
    }

    private void initVariables(String testName) {
        user = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        date = new Timestamp(new Date().getTime());
        StatisticService.statList.clear();
        questionList = testService.getQuestionsByTestName(testName);
        max = questionList.size();
    }

    private Statistic configureStatistic() {
        Statistic statistic = new Statistic();
        statistic.setCorrect(false);
        statistic.setQuestion(questionList.get(counter));
        statistic.setUser(userService.findUserById(user.getId()));
        statistic.setDate(date);
        return statistic;
    }

    void fillTestDB() {
        while (StatisticService.statList.size() != max) {
            statisticService.statList
                    .put(String.valueOf(questionList.get(counter)
                            .getId()), configureStatistic());
            counter++;
        }
        statisticService.saveMapOfStat(StatisticService.statList, date);
        clearCounter();
    }

    private void changeCorrectValue(Answer answer) {
        if (answer.isCorrect()) {
            statisticService.changeCorrectValue(date,
                    userService.findUserById(user.getId()),
                    questionList.get(counter), answer.isCorrect());
        }
    }

    private void clearCounter() {
        counter = 0;
    }
}