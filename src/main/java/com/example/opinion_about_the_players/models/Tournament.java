package com.example.opinion_about_the_players.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tournament")
public class Tournament {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    ////connection with teams
    @ManyToMany(mappedBy="tournament", fetch = FetchType.LAZY)
    private List<Team> team;

    ////connection with country
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private  Country country;


    private String nameTournament;

    @Column(name = "seasonTour")
    private String seasonTour;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTournament() {
        return nameTournament;
    }

    public void setNameTournament(String nameTournament) {
        this.nameTournament = nameTournament;
    }

    public String getDataTour(String seasonTour) {
        return seasonTour;
    }

    public void setDataTour(String seasonTour) {
        this.seasonTour = seasonTour;
    }

    public void Country(){
    };

    public void Country(String nameTournament, Country countries){
        this.nameTournament=nameTournament;
        this.country=countries;
    };

}