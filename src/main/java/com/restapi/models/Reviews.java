package com.restapi.models;

import jakarta.persistence.*;

@Entity
@Table(name="reviews_recommendations")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToMany(cascade = {CascadeType.DETACH,
                         CascadeType.MERGE,
                         CascadeType.REFRESH,
                         CascadeType.REMOVE})
    @JoinColumn(name = "author_id")
    private int authorId;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    @JoinColumn(name = "game_id")
    private int gameId;

    @Column(name="review_title")
    private String reviewTitle;

    @Column(name="rating")
    private int rating;

    @Column(name="review_description")
    private String reviewDescription;

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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
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

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", gameId=" + gameId +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", rating=" + rating +
                ", reviewDescription='" + reviewDescription + '\'' +
                '}';
    }
}
