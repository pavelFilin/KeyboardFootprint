package ru.filin.KeyboardFootprint.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class AuthenticationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date authenticationDate;
    private long averageKeyDistanceDifference;
    private long averageLetterDistanceDifference;
    public long allTimeDifference;

    private long averageKeyDistance;
    private long averageLetterDistance;
    private long allTime;

    private boolean result;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
