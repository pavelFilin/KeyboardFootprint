package ru.filin.KeyboardFootprint.service;

import org.springframework.stereotype.Service;
import ru.filin.KeyboardFootprint.entities.SimpleData;
import ru.filin.KeyboardFootprint.entities.User;
import ru.filin.KeyboardFootprint.repository.SimpleDateRepository;
import ru.filin.KeyboardFootprint.repository.UserDetailsRepository;

import java.util.List;

@Service
public class AuthenticationAttemptService {

    private SimpleDateRepository simpleDateRepository;
    private UserDetailsRepository userDetailsRepository;
    private AuthenticationCalculator authenticationCalculator;

    public AuthenticationAttemptService(SimpleDateRepository simpleDateRepository,
                                        UserDetailsRepository userDetailsRepository,
                                        AuthenticationCalculator authenticationCalculator) {
        this.simpleDateRepository = simpleDateRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.authenticationCalculator = authenticationCalculator;
    }

    public void tryAuthenticate(SimpleData authorisationAttempt) {
        User user = authorisationAttempt.getUser();
        List<SimpleData> previousAuthorisationAttempts = user.getAuthorisationAttempts();
        authenticationCalculator.calculate(user, authorisationAttempt, previousAuthorisationAttempts);
        previousAuthorisationAttempts.add(authorisationAttempt);
        userDetailsRepository.save(user);
    }
}
