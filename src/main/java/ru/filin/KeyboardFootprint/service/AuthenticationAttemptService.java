package ru.filin.KeyboardFootprint.service;

import org.springframework.stereotype.Service;
import ru.filin.KeyboardFootprint.entities.SimpleDate;
import ru.filin.KeyboardFootprint.entities.User;
import ru.filin.KeyboardFootprint.repository.SimpleDateRepository;
import ru.filin.KeyboardFootprint.repository.UserDetailsRepository;

import java.util.List;

@Service
public class AuthenticationAttemptService {

    private SimpleDateRepository simpleDateRepository;
    private UserDetailsRepository userDetailsRepository;
    private SimpleAuthenticationCalculator authenticationCalculator;

    public AuthenticationAttemptService(SimpleDateRepository simpleDateRepository, UserDetailsRepository userDetailsRepository, SimpleAuthenticationCalculator authenticationCalculator) {
        this.simpleDateRepository = simpleDateRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.authenticationCalculator = authenticationCalculator;
    }

    public void tryAuthenticate(SimpleDate authorisationAttempt) {
//        simpleDateRepository.save(authorisationAttempt);
        User user = authorisationAttempt.getUser();
        List<SimpleDate> authorisationAttempts = user.getAuthorisationAttempts();
        authenticationCalculator.calculate(user, authorisationAttempt)
    }
}
