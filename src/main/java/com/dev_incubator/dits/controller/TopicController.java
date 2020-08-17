package com.dev_incubator.dits.controller;

import com.dev_incubator.dits.service.dto.TopicDto;
import com.dev_incubator.dits.service.interfaces.TopicService;
import com.dev_incubator.dits.util.MessageSourceFacade;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static java.util.Objects.isNull;

@Controller
@RequestMapping(value = "/topics")
@AllArgsConstructor
public class TopicController {

    private final TopicService topicService;

    private final MessageSourceFacade messageSource;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String getAllTopics(Model model) {
        model.addAttribute("listTopics", topicService.getAllTopics());
        model.addAttribute("topic", new TopicDto());
        return "topic";
    }

    @GetMapping(value = "/edit/{topicId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editTopic(@PathVariable(value = "topicId", required = true) Long topicId,
                            Model model) {
        TopicDto topic = topicService.getTopicById(topicId);
        model.addAttribute("listTopics", topicService.getAllTopics());
        model.addAttribute("topic", topic);
        return "topic";
    }

    @GetMapping(value = "/delete/{topicId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteTopic(@PathVariable(value = "topicId", required = true) Long topicId,
                              RedirectAttributes redirectAttributes) {
        topicService.deleteTopicById(topicId);
        redirectAttributes.addFlashAttribute("report", messageSource.getMessage("topic.delete.success"));
        return "redirect:/topics";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String saveTopic(@ModelAttribute("topic") @Valid TopicDto topic,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listTopics", topicService.getAllTopics());
            return "topic";
        }
        if (!topicService.saveTopic(topic)) {
            model.addAttribute("listTopics", topicService.getAllTopics());
            model.addAttribute("topicNameError", messageSource.getMessage("topic.already.exist"));
            return "topic";
        }
        if (isNull(topic.getId())) {
            redirectAttributes.addFlashAttribute("report", messageSource.getMessage("topic.create.success"));
        } else {
            redirectAttributes.addFlashAttribute("report", messageSource.getMessage("topic.update.success"));
        }
        return "redirect:/topics";
    }
}
