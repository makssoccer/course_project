package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //connection with country
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    //connection with club
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    //connection with reviews
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Review> reviews;

    private String name;

    private LocalDateTime dob;

    private String nickname;

    private String fullText;

    @Column(name="urlPlayer", length = 255)
    private String urlPlayer;

    private Boolean isConfirmed;

    public void confirm() {
        this.isConfirmed=true;
    }
    private void cancelСonfirm() {
        this.isConfirmed=false;
    }

    public Player() {
    }
    public void info(){};
    public Player(String name, String nickname, String fullText, Team team, Country country, String urlPlayer, Boolean isConfirmed) {
        this.name = name;
        this.nickname = nickname;
        this.fullText = fullText;
        this.team = team;
        this.country = country;
        this.urlPlayer = urlPlayer;
        this.isConfirmed = isConfirmed;
    }

}
