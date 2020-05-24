package ru.filin.KeyboardFootprint.entities;

import lombok.Data;

@Data
public class KeyPressStamp {

    private long sumKeyDistance = 0;
    private long sumLetterDistance = 0;

    private int letterDistanceCount = 0;
    private int keyDistanceCount = 0;

    private long averageMin;
    private long averageMax;

    public void addKeyDistance(long time) {
        sumKeyDistance += time;
        keyDistanceCount++;
    }

    public void addLetterDistance(long time) {
        sumLetterDistance += time;
        letterDistanceCount++;
    }

    public long getAverageKeyDistance() {
        return sumKeyDistance/keyDistanceCount;
    }

    public long getAverageLetterDistance() {
        return sumLetterDistance/keyDistanceCount;
    }

    public long getAllTime() {
        return sumKeyDistance + sumLetterDistance;
    }
}
