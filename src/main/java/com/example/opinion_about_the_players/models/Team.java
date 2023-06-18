package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //connection with players
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players;

    //connection with coach
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Coach> coaches;

    //connection with tournament
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tournament_team", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "tournament_id"))
    private List<Tournament> tournament;

    public List<Tournament> getTournament() {
        return tournament;
    }

    private Boolean isConfirmed;

    private String nameTeam;

    @Column(name="urlTeam", length = 255)
    private String urlTeam;
    public Team() {
    }

    public List<Player> getPlayers() {
        List<Player> isConfir = new ArrayList<>();
        for(Player player : this.players ){
            if(player.getIsConfirmed()){
                isConfir.add(player);
            }
        }
        return isConfir;
    }

    public void setTourn(Tournament tournament) {
        this.tournament.add((Tournament) tournament);
    }
    public void setDelete(Tournament tournament) {
        this.tournament.remove((Tournament) tournament);
    }

    public Team(String nameTeam, List<Tournament> tournament, String urlTeam, Boolean isConfirmed) {
        this.nameTeam = nameTeam;
        this.tournament = tournament;
        this.urlTeam = urlTeam;
        this.isConfirmed = isConfirmed;
    }
}