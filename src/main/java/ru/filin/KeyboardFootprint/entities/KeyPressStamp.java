package ru.filin.KeyboardFootprint.entities;

import lombok.Data;

@Data
public class KeyPressStamp {
    long sumKeyDistance = 0;
    int keyDistanceCount = 0;
    long sumLetterDistance = 0;
    int letterDistanceCount = 0;

    public void addKeyDistance(long time) {
        sumKeyDistance += time;
        keyDistanceCount++;
    }

    public void addLetterDistance(long time) {
        sumLetterDistance += time;
        letterDistanceCount++;
    }

    public KeyPressStamp subtract(KeyPressStamp stamp) {
        KeyPressStamp result = new KeyPressStamp();

        result.setSumKeyDistance(sumKeyDistance - stamp.getSumKeyDistance());
        result.setKeyDistanceCount(keyDistanceCount - stamp.getKeyDistanceCount());

        result.setSumLetterDistance(letterDistanceCount - stamp.getLetterDistanceCount());
        result.setSumLetterDistance(letterDistanceCount - stamp.getLetterDistanceCount());

        return result;
    }

    public long getAverageKeyDistance() {
        return sumKeyDistance/keyDistanceCount;
    }

    public long getAverageLetterDistance() {
        return sumKeyDistance/keyDistanceCount;
    }

    public long getAllTime() {
        return sumKeyDistance + sumLetterDistance;
    }
}
