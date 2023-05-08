package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Team;
import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.Player;
import com.example.opinion_about_the_players.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerServise {

    private final PlayerRepository playerRepository;

    //заполняем
    @Transactional
    public Model getModelPlayers(Model model) {
        List<Player> players = playerRepository.getConfirmedPlayers();
        return model.addAttribute("players", players);
    }
    @Transactional
    public Model getNoApprovePlayers(Model model) {
        List<Player> players = playerRepository.getNoConfirmedPlayers();
        return model.addAttribute("players", players);
    }
    @Transactional
    public void savePlayer(String namePlayer, String nickname, String fullText, Team team, Country country, String urlPucture, Boolean isConfirmed) {
        Player player = new Player(namePlayer, nickname, fullText, team, country, urlPucture, isConfirmed);
        playerRepository.save(player);
    }
    @Transactional
    public boolean existsPlayer(Long id) {
        return playerRepository.existsById(id);
    }
    @Transactional
    public Model getInfoByPlayers(Long id, Model model) {

        Optional<Player> player = playerRepository.findById(id);
        List<Player> res = new ArrayList<>();
        player.ifPresent(res::add);
        return model.addAttribute("player", res);
    }
    @Transactional
    public void editPlayerToDB(Long id, String name, String nickname, String fullText, Team team, Country country, String urlPlayer, Boolean isConfirmed) {
        playerRepository.updatePlayer(name, nickname, fullText, team, country, urlPlayer, isConfirmed, id);
    }
    @Transactional
    public void deletePlayerOnDB(Long id) {
        Player player = playerRepository.findById(id).orElseThrow();
        playerRepository.delete(player);
    }

}
