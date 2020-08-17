package com.dev_incubator.dits.controller;

import com.dev_incubator.dits.persistence.entity.Topic;
import com.dev_incubator.dits.service.interfaces.TestService;
import com.dev_incubator.dits.service.interfaces.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/choose")
@AllArgsConstructor
public class ChooseTestController {

    private final TopicService topicService;

    private final TestService testService;

    @GetMapping(value = "/chooseTest")
    public String ChooseTest(Model model) {
        model.addAttribute("topics", topicService.findAll());
        return "userChoose";
    }

    @GetMapping(value = "/userChoose", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> searchTestName(@RequestParam(value = "topic", required = false) String topic) {
        List<String> list = topicService.findTestsByTopic(topic);
        return list;
    }

    @GetMapping(value = "/getThemes")
    public String goTest(@RequestParam(value = "topic", required = false) Topic topic, Model model) {
        model.addAttribute("tests", testService.findAll());
        return "testPage";
    }
}