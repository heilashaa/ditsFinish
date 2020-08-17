package com.dev_incubator.dits.exception;

import com.dev_incubator.dits.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ControllerAdvice(basePackages = "com.dev_incubator.dits")
@AllArgsConstructor
public class AdviceController {

    private final UserService userService;

    private final HttpSession session;

    @ExceptionHandler({UserAlreadyExistException.class})
    public String handleUserAlreadyExistException(
                                                UserAlreadyExistException e,
                                                HttpServletRequest request,
                                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("loginError", e.getMessage());
        return "redirect:" + request.getRequestURI();
    }

    @ExceptionHandler({UserHasNoStatisticException.class})
    public String handleUserHasNoStatisticException(
            UserHasNoStatisticException e,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("report", e.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler({UserNotFoundException.class, TopicNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleUserNotFoundException(AppException e, HttpServletRequest request) {
        return "400";
    }

//    @ExceptionHandler({Throwable.class})
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleThrowable(Throwable e, Model model) {
//        model.addAttribute("exception", e);
//        e.getStackTrace()
//        return "500";
//    }

    @ModelAttribute("countBlockedUsers")
    public Integer countBlockedUsers(){
        Integer countBlockedUsers = userService.countBlockUsers();
        if(countBlockedUsers > 0){
            return userService.countBlockUsers();
        }
        return 0;
    }
}
