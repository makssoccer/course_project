package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "player")
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    ////connection with country
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    ////connection with club
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    ////connection with reviews
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private List<Review> reviews;

    private String name;
    private String surname;
    private LocalDateTime dob;
    private String nickname;
    private String fullText;


    public Player() {
    }
    public Player(String name, String nickname, String fullText, Team team, Country country, LocalDateTime dob) {
        this.name = name;
        this.dob=dob;
        this.nickname = nickname;
        this.fullText = fullText;
        this.team = team;
        this.country=country;
    }

}
