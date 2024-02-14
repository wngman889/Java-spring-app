package com.restapi.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="profile_desc")
    private String profileDesc;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="steam_id")
    private String steamId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_reviews_recommendations", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "review_id")
    private List<Integer> reviewsIds;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "events_users", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "event_id")
    private List<Integer> eventIds;

    public User() {

    }

    public User(String username, String password, String email, String profileDesc,
                Date createdAt, String steamId, List<Integer> reviewsIds,
                List<Integer> eventIds) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileDesc = profileDesc;
        this.createdAt = createdAt;
        this.steamId = steamId;
        this.reviewsIds = reviewsIds;
        this.eventIds = eventIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public List<Integer> getReviewsIds() {
        return reviewsIds;
    }

    public void setReviewsIds(List<Integer> reviewsIds) {
        this.reviewsIds = reviewsIds;
    }

    public List<Integer> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Integer> eventIds) {
        this.eventIds = eventIds;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profileDesc='" + profileDesc + '\'' +
                ", createdAt=" + createdAt +
                ", steamId='" + steamId + '\'' +
                '}';
    }
}
