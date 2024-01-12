package com.restapi.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    private String description;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.REFRESH,
//            CascadeType.REMOVE})
//    @JoinTable(
//            name = "events_games",
//            joinColumns = @JoinColumn(name = "event_id"),
//            inverseJoinColumns = @JoinColumn(name = "game_id")
//    )
//    private List<Games> games;
    @ElementCollection
    @CollectionTable(name = "events_games", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "game_id")
    private List<Integer> gameIds;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.REFRESH,
//            CascadeType.REMOVE})
//    @JoinTable(
//            name = "events_users",
//            joinColumns = @JoinColumn(name = "event_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private List<User> users;
    @ElementCollection
    @CollectionTable(name = "events_users", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "user_id")
    private List<Integer> userIds;

    public Events() {

    }

    public Events(Date date, String description, List<Integer> gameIds, List<Integer> userIds) {
        this.date = date;
        this.description = description;
        this.gameIds = gameIds;
        this.userIds = userIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<Integer> gameIds) {
        this.gameIds = gameIds;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
