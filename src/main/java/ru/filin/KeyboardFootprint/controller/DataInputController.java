package ru.filin.KeyboardFootprint.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.filin.KeyboardFootprint.dto.ResultDTO;
import ru.filin.KeyboardFootprint.entities.AuthenticationResult;
import ru.filin.KeyboardFootprint.entities.DataEntry;
import ru.filin.KeyboardFootprint.entities.SimpleData;
import ru.filin.KeyboardFootprint.entities.User;
import ru.filin.KeyboardFootprint.service.AuthenticationAttemptService;

@RestController
public class DataInputController {
    @Value("${countRegistration}")
    private int countRegistration;

    private AuthenticationAttemptService authorisationAttemptService;

    public DataInputController(AuthenticationAttemptService authorisationAttemptService) {
        this.authorisationAttemptService = authorisationAttemptService;
    }

    @PostMapping("send-data")
    @ResponseBody
    public String receiveAuthorisationDate(@RequestBody SimpleData authorisationAttempt, @AuthenticationPrincipal User user) {
        authorisationAttempt.setUser(user);
        for (DataEntry dataEntry : authorisationAttempt.getKeysDown()) {
            dataEntry.setSimpleData(authorisationAttempt);
        }

        for (DataEntry dataEntry : authorisationAttempt.getKeysUp()) {
            dataEntry.setSimpleData(authorisationAttempt);
        }

        ResultDTO resultDTO = new ResultDTO();

        AuthenticationResult result = authorisationAttemptService.tryAuthenticate(authorisationAttempt);
        if (result == null) {
            resultDTO.setScore(-1);
            resultDTO.setAttemptNumber(user.getAuthorisationAttempts().size() - countRegistration);
            return new Gson().toJson(resultDTO);
        } else {
            resultDTO.setAuthenticated(result.isAuthenticated());
            resultDTO.setScore(result.getScore());
            resultDTO.setAttemptNumber(user.getAuthenticationResults().size());
        }

        return new Gson().toJson(resultDTO);
    }
}
