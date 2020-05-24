package ru.filin.KeyboardFootprint.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.filin.KeyboardFootprint.entities.User;

@Controller
public class MainController {

    @Value("${countRegistration}")
    private int countRegistration;

    @GetMapping("/main")
    public String getMainPage(@AuthenticationPrincipal User user, Model model) {
        int size = user.getAuthorisationAttempts().size();
        if (size < countRegistration) {
            size = size - countRegistration;
        } else {
            size = user.getAuthenticationResults().size();
        }
        model.addAttribute("attemptCount", size);
        return "main";
    }
}
