package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //connection with player
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User usr;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commentary> commentaries ;

    @Column(name = "anons")
    private String anons;

    @Column(name = "fullRewiew")
    private String fullReview;

    @Column(name = "timePost")
    private LocalDateTime timePost;


    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public Review() {
    }

    public Review(Player player, User usr, String anons, String fullReview, LocalDateTime timePost) {
        this.player = player;
        this.usr = usr;
        this.anons = anons;
        this.fullReview = fullReview;
        this.timePost = timePost;
    }
    public Review(Coach coach, User usr, String anons, String fullReview, LocalDateTime timePost) {
        this.coach = coach;
        this.usr = usr;
        this.anons = anons;
        this.fullReview = fullReview;
        this.timePost = timePost;
    }

}
