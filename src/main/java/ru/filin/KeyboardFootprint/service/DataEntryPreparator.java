package ru.filin.KeyboardFootprint.service;

import ru.filin.KeyboardFootprint.entities.DataEntry;
import ru.filin.KeyboardFootprint.entities.SimpleData;

import java.util.List;

public interface DataEntryPreparator {
    List<DataEntry> prepareDataEntry(SimpleData dates);
    List<DataEntry> prepareDataEntry(List<SimpleData> dates);
}
