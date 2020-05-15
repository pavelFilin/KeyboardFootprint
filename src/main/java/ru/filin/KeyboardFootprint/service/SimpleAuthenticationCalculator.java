package ru.filin.KeyboardFootprint.service;

import ru.filin.KeyboardFootprint.entities.DataEntry;
import ru.filin.KeyboardFootprint.entities.SimpleDate;
import ru.filin.KeyboardFootprint.entities.User;
import ru.filin.KeyboardFootprint.repository.UserDetailsRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SimpleAuthenticationCalculator implements AuthenticationCalculator {
    private static final long MAX_PRESS_DELAY = 300;
    private UserDetailsRepository userDetailsRepository;

    public SimpleAuthenticationCalculator(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public int calculate(User user, SimpleDate data) {
        List<SimpleDate> authorisationAttempts = user.getAuthorisationAttempts();
        List<DataEntry> dataEntries = new ArrayList<>();
        for (SimpleDate authorisationAttempt : authorisationAttempts) {
            dataEntries.addAll(authorisationAttempt.getKeysUp());
            dataEntries.addAll(authorisationAttempt.getKeysDown());
        }

        dataEntries.sort(Comparator.comparingLong(DataEntry::getDate));

        long averageKeyDistance = 0;
        int keyDistanceCount = 0;
        long averageLetterDistance = 0;
        int letterKeyDistance = 0;
        for (int i = 0; i < dataEntries.size() - 2; i++) {
            if (dataEntries.get(i).getDate() - dataEntries.get(i + 1).getDate() > MAX_PRESS_DELAY) {
                if (dataEntries.get(i).getEventCode().compareTo(dataEntries.get(i + 1).getEventCode()) > 0) {
                    if (i % 2 == 0) {
                        averageKeyDistance += dataEntries.get(i).getDate() - dataEntries.get(i + 1).getDate();
                    } else {
                        averageLetterDistance += dataEntries.get(i).getDate() - dataEntries.get(i + 2).getDate();
                    }
                }
            }
        }

        long newAverageKeyDistance = 0;
        int newKeyDistanceCount = 0;
        long newAverageLetterDistance = 0;
        int newLetterKeyDistance = 0;

        return 0;
    }
}
