package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameCountry;

    //connection with player
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Player> players;

    //connection with tournament
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Tournament> tournament;

    public Country() {
    }

    public Country(String nameCountry) {
        this.nameCountry = nameCountry;
    }
}