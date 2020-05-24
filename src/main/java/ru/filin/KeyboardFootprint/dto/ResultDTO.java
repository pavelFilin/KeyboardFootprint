package ru.filin.KeyboardFootprint.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private boolean authenticated;
    private double score;
    private int attemptNumber;
}
