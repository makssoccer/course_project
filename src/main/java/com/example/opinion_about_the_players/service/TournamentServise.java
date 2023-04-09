package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.Tournament;
import com.example.opinion_about_the_players.repository.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@AllArgsConstructor
public class TournamentServise {
    private final TournamentRepository tournamentRepository;

    public Model getModelTournaments(Model model) {
        Iterable<Tournament> tournaments = tournamentRepository.findAll();
        return model.addAttribute("tournaments", tournaments);
    }

    public void saveTournamentToDB(String nameTournament, Country countries) {
        if (!nameTournament.isEmpty()) {
            Tournament tournament = new Tournament(nameTournament, countries);
            tournamentRepository.save(tournament);
        }

    }
}
