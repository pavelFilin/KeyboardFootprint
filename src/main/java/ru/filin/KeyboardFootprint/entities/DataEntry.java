package ru.filin.KeyboardFootprint.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DataEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private EventCode eventCode;
    private long date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "simpleDate_id")
    private SimpleDate simpleDate;
}
