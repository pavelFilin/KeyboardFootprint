package ru.filin.KeyboardFootprint.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.filin.KeyboardFootprint.entities.SimpleData;
import ru.filin.KeyboardFootprint.entities.User;
import ru.filin.KeyboardFootprint.service.AuthenticationAttemptService;

@RestController
public class DataInputController {

    private AuthenticationAttemptService authorisationAttemptService;

    public DataInputController(AuthenticationAttemptService authorisationAttemptService) {
        this.authorisationAttemptService = authorisationAttemptService;
    }

    @PostMapping("send-data")
    public void receiveAuthorisationDate(@RequestBody SimpleData authorisationAttempt, @AuthenticationPrincipal User user) {
        authorisationAttempt.setUser(user);

        authorisationAttemptService.tryAuthenticate(authorisationAttempt);
    }
}
