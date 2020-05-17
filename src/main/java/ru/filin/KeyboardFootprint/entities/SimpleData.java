package ru.filin.KeyboardFootprint.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SimpleData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "simpleData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DataEntry> keysUp = new ArrayList<>();

    @OneToMany(mappedBy = "simpleData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DataEntry> keysDown = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
