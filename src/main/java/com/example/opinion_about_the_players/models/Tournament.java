package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //connection with teams
    @ManyToMany(mappedBy = "tournament", fetch = FetchType.LAZY)
    private List<Team> team;

    //connection with country
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")

    private Country country;

    private String nameTournament;

    public Tournament() {
    }

    public Tournament(String nameTournament, Country country) {
        this.nameTournament = nameTournament;
        this.country = country;
    }

}