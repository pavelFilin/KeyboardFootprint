package ru.filin.KeyboardFootprint.service;

import org.springframework.stereotype.Service;
import ru.filin.KeyboardFootprint.entities.*;
import ru.filin.KeyboardFootprint.repository.UserDetailsRepository;

import java.util.Date;
import java.util.List;

@Service
public class SimpleAuthenticationCalculator implements AuthenticationCalculator {
    private static final long MAX_PRESS_DELAY = 300;

    private UserDetailsRepository userDetailsRepository;
    private DataEntryPreparator dataEntryPreparator;

    public SimpleAuthenticationCalculator(UserDetailsRepository userDetailsRepository,
                                          DataEntryPreparator dataEntryPreparator) {
        this.userDetailsRepository = userDetailsRepository;
        this.dataEntryPreparator = dataEntryPreparator;
    }

    @Override
    public AuthenticationResult calculate(User user, SimpleData newAuthenticationAttempt, List<SimpleData> authenticationAttempts) {
        KeyPressStamp previousKeyPressStamp = calculateBaseKeyPressParameters(dataEntryPreparator.prepareDataEntry(authenticationAttempts));
        KeyPressStamp newKeyPressStamp = calculateBaseKeyPressParameters(dataEntryPreparator.prepareDataEntry(newAuthenticationAttempt));

        KeyPressStamp difference = newKeyPressStamp.subtract(previousKeyPressStamp);

        AuthenticationResult result = new AuthenticationResult();
        result.setAuthenticationDate(new Date());

        result.setAllTime(newKeyPressStamp.getAllTime());
        result.setAllTimeDifference(difference.getAllTime());

        result.setAverageKeyDistance(newKeyPressStamp.getAverageKeyDistance());
        result.setAverageKeyDistanceDifference(difference.getAverageKeyDistance());

        result.setAverageLetterDistance(newKeyPressStamp.getAverageLetterDistance());
        result.setAverageLetterDistanceDifference(difference.getAverageLetterDistance());

        user.getAuthenticationResults().add(result);

        //todo calculate

        return result;
    }

    private KeyPressStamp calculateBaseKeyPressParameters(List<DataEntry> dataEntries) {
        KeyPressStamp keyPressStamp = new KeyPressStamp();

        for (int i = 0; i < dataEntries.size() - 2; i++) {
            if (dataEntries.get(i).getDate() - dataEntries.get(i + 1).getDate() > MAX_PRESS_DELAY
                    && dataEntries.get(i).getEventCode().compareTo(dataEntries.get(i + 1).getEventCode()) > 0) {
                if (i % 2 == 0) {
                    keyPressStamp.addKeyDistance(dataEntries.get(i).getDate() - dataEntries.get(i + 1).getDate());
                } else {
                    keyPressStamp.addLetterDistance(dataEntries.get(i).getDate() - dataEntries.get(i + 2).getDate());
                }
            }
        }

        return keyPressStamp;
    }
}
