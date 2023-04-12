package com.example.opinion_about_the_players.models;

import javax.persistence.*;
import java.util.List;

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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Coach> getCoachs() {
        return coachs;
    }

    public void setCoachs(List<Coach> coachs) {
        this.coachs = coachs;
    }

    public List<Tournament> getTournament() {
        return tournament;
    }

    public void setTournament(List<Tournament> tournament) {
        this.tournament = tournament;
    }

    public void setTournaments(List<Tournament> tournaments) {
        tournament.addAll(tournaments);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public Team(String nameTeam, List<Tournament> tournament) {
        this.nameTeam = nameTeam;
        this.tournament = tournament;
    }

    public Team() {
    }
}