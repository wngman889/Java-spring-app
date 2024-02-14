package com.restapi.models;

import jakarta.persistence.*;

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

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "games_reviews_recommendations", joinColumns = @JoinColumn(name = "review_id"))
    @Column(name = "game_id")
    private List<Integer> gameIds;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_reviews_recommendations", joinColumns = @JoinColumn(name = "review_id"))
    @Column(name = "user_id")
    private List<Integer> userIds;

    public Reviews() {

    }

    public Reviews(String reviewTitle, int rating, String reviewDescription, List<Integer> gameIds, List<Integer> userIds) {
        this.reviewTitle = reviewTitle;
        this.rating = rating;
        this.reviewDescription = reviewDescription;
        this.gameIds = gameIds;
        this.userIds = userIds;
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
        return "Reviews{" +
                "id=" + id +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", rating=" + rating +
                ", reviewDescription='" + reviewDescription + '\'' +
                '}';
    }
}
