package com.restapi.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="games")
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="game_desc")
    private String gameDesc;

    @Column(name="genre")
    private String genre;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    @JoinTable(
            name = "games_reviews_recommendations",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<Reviews> gameReviews;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    @JoinTable(
            name = "events_games",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Events> gameEvents = new ArrayList<>();

    public Games() {

    }

    public Games(String title, String gameDesc, String genre) {
        this.title = title;
        this.gameDesc = gameDesc;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGameDesc() {
        return gameDesc;
    }

    public void setGameDesc(String gameDesc) {
        this.gameDesc = gameDesc;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Reviews> getGameReviews() {
        return gameReviews;
    }

    public void setGameReviews(List<Reviews> gameReviews) {
        this.gameReviews = gameReviews;
    }

    public List<Events> getGameEvents() {
        return gameEvents;
    }

    public void setGameEvents(List<Events> gameEvents) {
        this.gameEvents = gameEvents;
    }

    @Override
    public String toString() {
        return "Games{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", gameDesc='" + gameDesc + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
