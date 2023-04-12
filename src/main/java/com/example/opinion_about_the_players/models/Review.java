package com.example.opinion_about_the_players.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //connection with player
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User usr;

    @Column(name = "anons")
    private String anons;

    @Column(name = "fullRewiew")
    private String fullReview;

    @Column(name = "timePost")
    private LocalDateTime timePost;

    public User getUser() {
        return usr;
    }

    public void setUser(User user) {
        this.usr = user;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFullReview() {
        return fullReview;
    }

    public void setFullReview(String fullRewiew) {
        this.fullReview = fullRewiew;
    }

    public LocalDateTime getTimePost() {
        return timePost;
    }

    public void setTimePost(LocalDateTime timePost) {
        this.timePost = timePost;
    }

    public Review() {
    }

    public Review(Player player, User usr, String anons, String fullReview, LocalDateTime timePost) {
        this.player = player;
//        this.coach = coach;
        this.usr = usr;
        this.anons = anons;
        this.fullReview = fullReview;
        this.timePost = timePost;
    }
}
