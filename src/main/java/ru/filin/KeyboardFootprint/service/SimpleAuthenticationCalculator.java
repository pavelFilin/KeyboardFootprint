package ru.filin.KeyboardFootprint.service;

import org.springframework.stereotype.Service;
import ru.filin.KeyboardFootprint.entities.*;
import ru.filin.KeyboardFootprint.repository.UserDetailsRepository;

import java.util.Date;
import java.util.List;

@Service
public class SimpleAuthenticationCalculator implements AuthenticationCalculator {
    private static final long MAX_PRESS_DELAY = 300;
    private static final long LETTER_DELAY = 1500;

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

        AuthenticationResult result = new AuthenticationResult();
        result.setAuthenticationDate(new Date());

        result.setAllTime(newKeyPressStamp.getAllTime());
        result.setAllTimeDifference(newKeyPressStamp.getAllTime() - previousKeyPressStamp.getAllTime() / authenticationAttempts.size());

        result.setAverageKeyDistance(newKeyPressStamp.getAverageKeyDistance());
        result.setAverageKeyDistanceDifference(newKeyPressStamp.getAverageKeyDistance() - previousKeyPressStamp.getAverageKeyDistance());

        result.setAverageLetterDistance(newKeyPressStamp.getAverageLetterDistance());
        result.setAverageLetterDistanceDifference(newKeyPressStamp.getAverageLetterDistance() - previousKeyPressStamp.getAverageLetterDistance());

        double deviationAllTime = getDeviation(result.getAllTime() + result.getAllTimeDifference(), result.getAllTime());
        double deviationKeyDistance = getDeviation(result.getAverageKeyDistanceDifference() + result.getAverageKeyDistance(), result.getAverageKeyDistance());
        double deviationLetterDistance = getDeviation(result.getAverageLetterDistanceDifference() + result.getAverageLetterDistance(), result.getAverageLetterDistance());

        double decision = deviationAllTime * 0.2 + deviationLetterDistance * 0.45 + 0.35 * deviationKeyDistance;

        result.setScore(decision);

        return result;
    }

    private KeyPressStamp calculateBaseKeyPressParameters(List<DataEntry> dataEntries) {
        KeyPressStamp keyPressStamp = new KeyPressStamp();

        for (int i = 0; i < dataEntries.size() - 1; i++) {
            long difference = dataEntries.get(i + 1).getDate() - dataEntries.get(i).getDate();
            if (dataEntries.get(i).getEventCode() == EventCode.KEYDOWN
                    && dataEntries.get(i + 1).getEventCode() == EventCode.KEYUP
                    && difference < MAX_PRESS_DELAY) {
                keyPressStamp.addKeyDistance(difference);
                calculateEdgeValues(keyPressStamp, difference);
            } else if (dataEntries.get(i).getEventCode() == EventCode.KEYUP
                    && dataEntries.get(i + 1).getEventCode() == EventCode.KEYDOWN
                    && difference < LETTER_DELAY) {
                keyPressStamp.addLetterDistance(difference);
                calculateEdgeValues(keyPressStamp, difference);
            }
        }

        return keyPressStamp;
    }

    private void calculateEdgeValues(KeyPressStamp keyPressStamp, long difference) {
        if (keyPressStamp.getAverageMax() > difference) {
            keyPressStamp.setAverageMax(difference);
        }

        if (keyPressStamp.getAverageMin() < difference) {
            keyPressStamp.setAverageMin(difference);
        }
    }

    private double getDeviation(double current, double average) {
        if (current > average) {
            return 2 - (current / average);
        } else {
            return 2 - (average / current);
        }

    }


}
