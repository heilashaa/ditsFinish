package com.dev_incubator.dits.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "main"})
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TUTOR', 'USER')")
    public String showUserForm() {
        return "main";
    }
}
