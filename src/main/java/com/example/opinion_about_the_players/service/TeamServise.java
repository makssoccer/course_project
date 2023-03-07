package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Team;
import com.example.opinion_about_the_players.models.Tournament;
import com.example.opinion_about_the_players.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServise {
    @Autowired
    private TeamRepository teamRepository;

    public Model getTeams(Model model){
        Iterable<Team> teams = teamRepository.findAll();
        return model.addAttribute("teams",teams);
    }
    public  void saveTeamToDB(String nameTeam, List<Tournament> tournament)
    {
        if (!nameTeam.equals("")) {
        Team team =new Team();
        team.setNameTeam(nameTeam);
        team.setTournament(tournament);
        teamRepository.save(team);
        }
    }
    public Model getInfoByTeams(long id, Model model){
        Optional<Team> team = teamRepository.findById(id);
//        Club club1 = clubRepository.findById(id);
        ArrayList<Team> resol= new ArrayList<>();
        team.ifPresent(resol::add);
        return model.addAttribute("team",resol);
    }
    public  void editTeamToDB(long id, String nameTeam, List<Tournament> tournament)
    {
        Team team = teamRepository.findById(id).orElseThrow();
        team.setNameTeam(nameTeam);
        team.setTournaments(tournament);
        teamRepository.save(team);
    }
    public  void deleteTeamOnDB(long id)
    { Team team = teamRepository.findById(id).orElseThrow();
        teamRepository.delete(team);}
}
