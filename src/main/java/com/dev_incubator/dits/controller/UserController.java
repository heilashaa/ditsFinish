package com.dev_incubator.dits.controller;

import com.dev_incubator.dits.config.security.CustomUserDetails;
import com.dev_incubator.dits.persistence.entity.Role;
import com.dev_incubator.dits.service.dto.UserDto;
import com.dev_incubator.dits.service.interfaces.MailService;
import com.dev_incubator.dits.service.interfaces.RoleService;
import com.dev_incubator.dits.service.interfaces.UserService;
import com.dev_incubator.dits.util.MessageSourceFacade;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Controller
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final MessageSourceFacade messageSource;

    private final MailService mailService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String getAllUsers(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String showUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user";
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String saveUser(@ModelAttribute("user") @Valid UserDto user,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "user";
        }
        if (!userService.saveUser(user)) {
            model.addAttribute("loginError", messageSource.getMessage("user.already.exist"));
            return "user";
        }
        if (isNull(user.getId())) {
            redirectAttributes.addFlashAttribute("report", messageSource.getMessage("user.create.success"));
        } else {
            redirectAttributes.addFlashAttribute("report", messageSource.getMessage("user.update.success"));
        }
        return "redirect:/users";
    }

    @GetMapping(value = "/edit/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String showEditUserForm(@PathVariable(value = "userId", required = true) Long userId,
                                   Model model) {
        UserDto user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/login")
    public String login(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout,
            Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            model.asMap().clear();
            return "redirect:/";
        }
        if (nonNull(error) && error.equals("blocked")) {
            model.addAttribute("report", messageSource.getMessage("user.blocked.by.admin"));
        }
        if (nonNull(error) && error.equals("bad_credential")) {
            model.addAttribute("report", messageSource.getMessage("user.bad.credentials"));
        }
        if (nonNull(logout)) {
            model.addAttribute("report", messageSource.getMessage("user.logout.success"));
        }
        return "login";
    }

    @GetMapping(value = "/logout")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TUTOR', 'USER')")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.asMap().clear();
        return "redirect:/users/login?logout";
    }

    @GetMapping(value = "/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("user") @Valid UserDto user,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.getErrorCount() > 1) {
            return "registration";
        }
        String password = user.getPassword();
        if (!userService.saveUser(user)) {
            model.addAttribute("loginError", messageSource.getMessage("user.already.exist"));
            return "registration";
        }
        redirectAttributes.addFlashAttribute("report", messageSource.getMessage("user.registration.success"));
        String emailMessage = mailService.getRegistrationMessage(user.getFirstName(), user.getLastName(), user.getLogin(), password);
        mailService.sendEmail(user.getEmail(), "DITS | Registration", emailMessage);
        return "redirect:/users/login";
    }

    @ModelAttribute("roleList")
    public List<Role> getRoleList() {
        return roleService.getAll();
    }

    @GetMapping(value = "/re-block/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String changeUserBlockStatus(
            @PathVariable(value = "userId", required = true) Long userId,
            RedirectAttributes redirectAttributes) {
        UserDto user = userService.getUserById(userId);
        if (userService.changeBlockStatus(userId)) {
            redirectAttributes.addFlashAttribute("report", messageSource.getMessage("user.unblocked"));
            String emailMessage = mailService.getUnblockMessage(user.getFirstName(), user.getLastName());
            mailService.sendEmail(user.getEmail(), "DITS | Unblocked", emailMessage);
        } else {
            redirectAttributes.addFlashAttribute("report", messageSource.getMessage("user.blocked"));
            String emailMessage = mailService.getBlockMessage(user.getFirstName(), user.getLastName());
            mailService.sendEmail(user.getEmail(), "DITS | Blocked", emailMessage);
        }
        return "redirect:/users";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TUTOR', 'USER')")
    public String showPersonalProfile(Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto user = userService.getUserById(userDetails.getId());
        model.addAttribute("user", user);
        return "user-profile";
    }

    @PostMapping(value = "/profile")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TUTOR', 'USER')")
    public String updatePersonalProfile(@ModelAttribute("user") @Valid UserDto user,
                                        BindingResult bindingResult,
                                        Model model,
                                        RedirectAttributes redirectAttributes) {
        UserDto existUser = userService.getUserById(user.getId());
        user.setEnabled(true);
        user.setRoles(existUser.getRoles());
        if (bindingResult.getErrorCount() > 1) {
            model.addAttribute("user", user);
            return "user-profile";
        }
        if (!userService.saveUser(user)) {
            model.addAttribute("loginError", messageSource.getMessage("user.already.exist"));
            return "user-profile";
        }
        redirectAttributes.addFlashAttribute("report", messageSource.getMessage("user.profile.update.success"));
        return "redirect:/users/profile";
    }
}
