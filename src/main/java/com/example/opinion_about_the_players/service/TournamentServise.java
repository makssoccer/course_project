package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.Tournament;
import com.example.opinion_about_the_players.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TournamentServise {
    @Autowired
    private TournamentRepository tournamentRepository;

    public Model getTournaments(Model model){
        Iterable<Tournament> tournaments = tournamentRepository.findAll();
        return model.addAttribute("tournaments",tournaments);
    }
    public  void saveTournamentToDB(String nameTournament, Country countries)
    {
        if (!nameTournament.equals("")) {
            Tournament tournament =new Tournament();
            tournament.setNameTournament(nameTournament);
            tournament.setCountry(countries);
            tournamentRepository.save(tournament);
        }

    }
}
