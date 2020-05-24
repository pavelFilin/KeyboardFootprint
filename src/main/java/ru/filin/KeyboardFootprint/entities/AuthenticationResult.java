package ru.filin.KeyboardFootprint.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    private boolean authenticated;
    private double score;


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAuthenticationDate() {
        return authenticationDate;
    }

    public void setAuthenticationDate(Date authenticationDate) {
        this.authenticationDate = authenticationDate;
    }

    public long getAverageKeyDistanceDifference() {
        return averageKeyDistanceDifference;
    }

    public void setAverageKeyDistanceDifference(long averageKeyDistanceDifference) {
        this.averageKeyDistanceDifference = averageKeyDistanceDifference;
    }

    public long getAverageLetterDistanceDifference() {
        return averageLetterDistanceDifference;
    }

    public void setAverageLetterDistanceDifference(long averageLetterDistanceDifference) {
        this.averageLetterDistanceDifference = averageLetterDistanceDifference;
    }

    public long getAllTimeDifference() {
        return allTimeDifference;
    }

    public void setAllTimeDifference(long allTimeDifference) {
        this.allTimeDifference = allTimeDifference;
    }

    public long getAverageKeyDistance() {
        return averageKeyDistance;
    }

    public void setAverageKeyDistance(long averageKeyDistance) {
        this.averageKeyDistance = averageKeyDistance;
    }

    public long getAverageLetterDistance() {
        return averageLetterDistance;
    }

    public void setAverageLetterDistance(long averageLetterDistance) {
        this.averageLetterDistance = averageLetterDistance;
    }

    public long getAllTime() {
        return allTime;
    }

    public void setAllTime(long allTime) {
        this.allTime = allTime;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
