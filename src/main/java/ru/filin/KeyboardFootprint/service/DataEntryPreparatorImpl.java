package ru.filin.KeyboardFootprint.service;

import org.springframework.stereotype.Service;
import ru.filin.KeyboardFootprint.entities.DataEntry;
import ru.filin.KeyboardFootprint.entities.SimpleData;

import java.util.*;

@Service
public class DataEntryPreparatorImpl implements DataEntryPreparator {
    @Override
    public List<DataEntry> prepareDataEntry(SimpleData date) {
       return prepareDataEntry(Collections.singletonList(date));
    }

    @Override
    public List<DataEntry> prepareDataEntry(List<SimpleData> dates) {
        List<DataEntry> dataEntries = new ArrayList<>();
        for (SimpleData authorisationAttempt : dates) {
            dataEntries.addAll(authorisationAttempt.getKeysUp());
            dataEntries.addAll(authorisationAttempt.getKeysDown());
        }

        dataEntries.sort(Comparator.comparingLong(DataEntry::getDate));

        return dataEntries;
    }
}
