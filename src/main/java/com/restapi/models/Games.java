package com.restapi.models;

import jakarta.persistence.*;

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

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "games_reviews_recommendations", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "review_id")
    private List<Integer> reviewIds;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "events_games", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "event_id")
    private List<Integer> eventIds;

    public Games() {

    }

    public Games(String title, String gameDesc, String genre, List<Integer> reviewIds, List<Integer> eventIds) {
        this.title = title;
        this.gameDesc = gameDesc;
        this.genre = genre;
        this.reviewIds = reviewIds;
        this.eventIds = eventIds;
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

    public List<Integer> getReviewIds() {
        return reviewIds;
    }

    public void setReviewIds(List<Integer> reviewIds) {
        this.reviewIds = reviewIds;
    }

    public List<Integer> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Integer> eventIds) {
        this.eventIds = eventIds;
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
