package com.example.opinion_about_the_players.conrtrollers;

import com.example.opinion_about_the_players.models.Team;
import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.User;
import com.example.opinion_about_the_players.repository.PlayerRepository;
import com.example.opinion_about_the_players.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerServise playerServise;
    @Autowired
    private TeamServise teamServise;
    @Autowired
    private CountryServise countryServise;
    @Autowired
    private ReviewServise reviewServise;
    @Autowired
    private UserService userService;

    @GetMapping("/players")
    public String playerMain(Model model){
        playerServise.getPlayers(model);
        userService.getUsers(model);
        return "playerPackage/players";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/players/add")
    public String playerAdd( Model model){
        teamServise.getTeams(model);
        countryServise.getCountries(model);

        return "playerPackage/players-add";

    }
    /////////create player
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/players/add")
    public String playerPostAdd(@RequestParam String name, @RequestParam  String nickname, @RequestParam String fullText, @RequestParam Team team, @RequestParam Country country, Model model) {
        playerServise.savePlayerToDB(name, nickname, fullText, team, country);
        return "redirect:/players";
    }
    ////////Страница конкретного Игрока

    @GetMapping("/players/{id}")
    public String playerDetails(@PathVariable(value="id") long id, Model model){
    if(!playerRepository.existsById(id)){
            return "redirect:/players";
        }
        playerServise.getInfoByPlayers(id,model);
        reviewServise.getPlayerReviews(id,model);
        return "playerPackage/players-details";
    }

    @PostMapping("/players/{id}")
    public String playerPostReview(@PathVariable(value="id") long id, @RequestParam String anons, @RequestParam String fullReview, Model model){
        reviewServise.saveRiviewsPlayer(anons, fullReview, id);
        return "redirect:/players";
    }
    //////Получение данных об Игроке для его дальнейшего редактирования
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/players/{id}edit")
    public String playerEdit(@PathVariable(value="id") long id, Model model){
        if(!playerRepository.existsById(id)){
            return "redirect:/players";}
        playerServise.getInfoByPlayers(id,model);
        teamServise.getTeams(model);
        countryServise.getCountries(model);
        return "playerPackage/players-edit";
    }
    ////Редактирование данных Игрока
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/players/{id}edit")
    public String playerPostUbdate(@PathVariable(value="id") long id, @RequestParam String name, @RequestParam Team team, @RequestParam Country country , @RequestParam String nickname, @RequestParam String fullText, Model model) {
        playerServise.editPlayerToDB(id, name, nickname, fullText, team,country);
        return "redirect:/players";}

    ////Удаление Игрока
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/players/{id}remove")
    public String playerPostDelete(@PathVariable(value="id") long id,Model model) {
        playerServise.deletePlayerOnDB(id);
        return "redirect:/players";
    }
}
