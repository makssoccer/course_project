package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Team;
import com.example.opinion_about_the_players.models.Tournament;
import com.example.opinion_about_the_players.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamServise {
    private final TeamRepository teamRepository;

    public Model getModelTeams(Model model) {
        List<Team> teams = teamRepository.findAll();
        return model.addAttribute("teams", teams);
    }

    public void saveTeam(String nameTeam, List<Tournament> tournament) {
        if (!nameTeam.isEmpty()) {
            Team team = new Team(nameTeam, tournament);
            teamRepository.save(team);
        }
    }

    public Model getInfoByTeams(long id, Model model) {
        Optional<Team> team = teamRepository.findById(id);
        List<Team> resol = new ArrayList<>();
        team.ifPresent(resol::add);
        return model.addAttribute("team", resol);
    }

    public void editTeam(long id, String nameTeam, List<Tournament> tournament) {
        Team team = teamRepository.findById(id).orElseThrow();
        team.setNameTeam(nameTeam);
        team.setTournaments(tournament);
        teamRepository.save(team);
    }

    public void deleteTeamOnDB(long id) {
        Team team = teamRepository.findById(id).orElseThrow();
        teamRepository.delete(team);
    }
}
