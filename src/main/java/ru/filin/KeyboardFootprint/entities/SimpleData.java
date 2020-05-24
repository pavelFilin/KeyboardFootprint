package ru.filin.KeyboardFootprint.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DataEntry> getKeysUp() {
        return keysUp;
    }

    public void setKeysUp(List<DataEntry> keysUp) {
        this.keysUp = keysUp;
    }

    public List<DataEntry> getKeysDown() {
        return keysDown;
    }

    public void setKeysDown(List<DataEntry> keysDown) {
        this.keysDown = keysDown;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
