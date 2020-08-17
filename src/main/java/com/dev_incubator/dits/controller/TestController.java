package com.dev_incubator.dits.controller;

import com.dev_incubator.dits.service.interfaces.TestService;
import com.dev_incubator.dits.service.interfaces.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/tests")
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    private final TopicService topicService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TUTOR')")
    public String getAllTestsInformation(Model model) {
        model.addAttribute("listTopicsWithTests", topicService.getAllTopicsWithTests());
        return "test-list";
    }
}
