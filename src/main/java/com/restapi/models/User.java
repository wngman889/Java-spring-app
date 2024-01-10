package com.restapi.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
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

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private List<Reviews> userReviews = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private List<Reviews> userEvents = new ArrayList<>();

    public User() {

    }

    public User(String username, String password, String email, String profileDesc, Date createdAt, String steamId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileDesc = profileDesc;
        this.createdAt = createdAt;
        this.steamId = steamId;
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

    public List<Reviews> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<Reviews> userReviews) {
        this.userReviews = userReviews;
    }

    public List<Reviews> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<Reviews> userEvents) {
        this.userEvents = userEvents;
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
