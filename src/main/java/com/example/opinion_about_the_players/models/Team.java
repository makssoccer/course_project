package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //connection with players
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players;

    //connection with coach
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coach_team", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "coach_id"))
    private List<Coach> coachs;

    //connection with tournament
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tournament_team", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "tournament_id"))
    private List<Tournament> tournament;

    private String nameTeam;
    public Team() {
    }

    public Team(String nameTeam, List<Tournament> tournament) {
        this.nameTeam = nameTeam;
        this.tournament = tournament;
    }


}