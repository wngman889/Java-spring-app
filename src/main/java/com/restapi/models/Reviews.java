package com.restapi.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="reviews_recommendations")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="review_title")
    private String reviewTitle;

    @Column(name="rating")
    private int rating;

    @Column(name="review_description")
    private String reviewDescription;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private List<Games> games = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private List<User> users = new ArrayList<>();

    public Reviews() {

    }

    public Reviews(String reviewTitle, int rating, String reviewDescription) {
        this.reviewTitle = reviewTitle;
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public List<Games> getGames() {
        return games;
    }

    public void setGames(List<Games> games) {
        this.games = games;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", rating=" + rating +
                ", reviewDescription='" + reviewDescription + '\'' +
                '}';
    }
}
