package com.example.opinion_about_the_players.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    ////connection with player
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Player> players;

    ////connection with tournament
    @OneToMany(mappedBy="country", fetch = FetchType.LAZY)
    private List<Tournament> tournament;

    public List<Tournament> getTournament() {
        return tournament;
    }

    public void setTournament(List<Tournament> tournament) {
        this.tournament = tournament;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    private String nameCountry;

    public Country() {
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameСountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Country(String nameCountry) {
        this.nameCountry = nameCountry;
    }
}