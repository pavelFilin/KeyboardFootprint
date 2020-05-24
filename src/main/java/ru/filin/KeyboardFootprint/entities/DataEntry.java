package ru.filin.KeyboardFootprint.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
public class DataEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private EventCode eventCode;
    private long date;
    private char key;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "simple_data_id")
    private SimpleData simpleData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventCode getEventCode() {
        return eventCode;
    }

    public void setEventCode(EventCode eventCode) {
        this.eventCode = eventCode;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public SimpleData getSimpleData() {
        return simpleData;
    }

    public void setSimpleData(SimpleData simpleData) {
        this.simpleData = simpleData;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }
}
