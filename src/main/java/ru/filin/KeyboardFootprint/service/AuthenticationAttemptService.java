package ru.filin.KeyboardFootprint.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.filin.KeyboardFootprint.entities.AuthenticationResult;
import ru.filin.KeyboardFootprint.entities.FakeAuthenticationResult;
import ru.filin.KeyboardFootprint.entities.SimpleData;
import ru.filin.KeyboardFootprint.entities.User;
import ru.filin.KeyboardFootprint.repository.SimpleDateRepository;
import ru.filin.KeyboardFootprint.repository.UserDetailsRepository;

import java.util.List;

@Service
public class AuthenticationAttemptService {

    @Value("${countRegistration}")
    private int countRegistration;

    @Value("${profile}")
    private String profile;

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

    public AuthenticationResult tryAuthenticate(SimpleData authorisationAttempt) {
        User user = authorisationAttempt.getUser();
        List<SimpleData> previousAuthorisationAttempts = user.getAuthorisationAttempts();

        if (user.getAuthorisationAttempts().size() < countRegistration) {
            previousAuthorisationAttempts.add(authorisationAttempt);
            simpleDateRepository.save(authorisationAttempt);
            return null;
        }

        if (profile.equals("debug")) {
            fakeCalculate(authorisationAttempt, user);
        }

        AuthenticationResult result = authenticationCalculator.calculate(user, authorisationAttempt, previousAuthorisationAttempts);
        result.setUser(user);

        user.getAuthenticationResults().add(result);

        previousAuthorisationAttempts.add(authorisationAttempt);
        userDetailsRepository.save(user);

        return result;
    }

    private void fakeCalculate(SimpleData authorisationAttempt, User user) {
        List<User> allUsers = userDetailsRepository.findAll();
        for (User u : allUsers) {
            if (!u.equals(user)) {
                List<SimpleData> simpleDate = simpleDateRepository.findByUser(u);
                if (simpleDate.size() > countRegistration) {
                    AuthenticationResult fakeResult = authenticationCalculator.calculate(u, authorisationAttempt, simpleDate);
                    FakeAuthenticationResult fakeAuthenticationResult = new FakeAuthenticationResult();
                    fakeAuthenticationResult.setAuthenticated(fakeResult.isAuthenticated());
                    fakeAuthenticationResult.setAuthenticatedUserId(u.getId());
                    fakeAuthenticationResult.setUser(user);
                    fakeAuthenticationResult.setScore(fakeResult.getScore());
                }
            }
        }
    }
}
