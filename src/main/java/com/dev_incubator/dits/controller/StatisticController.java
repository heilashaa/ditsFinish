package com.dev_incubator.dits.controller;

import com.dev_incubator.dits.config.security.CustomUserDetails;
import com.dev_incubator.dits.service.dto.UserDto;
import com.dev_incubator.dits.service.interfaces.StatisticService;
import com.dev_incubator.dits.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/statistic")
@AllArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    private final UserService userService;

    @GetMapping(value = "/by-tests")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TUTOR')")
    public String getStatisticByTests(Model model) {
        model.addAttribute("listStatistic", statisticService.getStatisticByTests());
        model.addAttribute("statisticType", "by-tests");
        return "statistic";
    }

    @GetMapping(value = "/by-questions")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TUTOR')")
    public String getStatisticByQuestions(Model model) {
        model.addAttribute("listStatistic", statisticService.getStatisticByQuestions());
        model.addAttribute("statisticType", "by-questions");
        return "statistic";
    }

    @GetMapping(value = "/by-users")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TUTOR')")
    public String getStatisticByUsers(Model model) {
        model.addAttribute("listStatistic", statisticService.getStatisticByUsers());
        model.addAttribute("statisticType", "by-users");
        return "statistic";
    }

    @GetMapping(value = "/personal")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TUTOR', 'USER')")
    public String getPersonalStatistic(Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto user = userService.getUserById(userDetails.getId());
        model.addAttribute("listStatistic", statisticService.getPersonalStatistic(user));
        model.addAttribute("statisticType", "personal");
        return "statistic";
    }
}
