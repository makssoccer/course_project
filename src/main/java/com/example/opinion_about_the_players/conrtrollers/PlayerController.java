package com.example.opinion_about_the_players.conrtrollers;

import com.example.opinion_about_the_players.models.Team;
import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PlayerController {
    private final PlayerServise playerServise;
    private final TeamServise teamServise;
    private final CountryServise countryServise;
    private final ReviewServise reviewServise;
    private final UserService userService;

    @GetMapping("/players")
    public String playerMain(Model model) {
        playerServise.getModelPlayers(model);
        userService.getModelUsers(model);
        return "playerPackage/players";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/players/add")
    public String playerAdd(Model model) {
        teamServise.getModelTeams(model);
        countryServise.getModelCount(model);

        return "playerPackage/players-add";

    }

    //create player
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/players/add")
    public String playerPostAdd(@RequestParam String name,
                                @RequestParam String nickname,
                                @RequestParam String fullText,
                                @RequestParam(value = "team", required = false) Team team,
                                @RequestParam(value = "country", required = false) Country country,
                                @RequestParam(value = "urlPlayer", required = false) String urlPlayer,
                                Model model) {
        playerServise.savePlayer(name, nickname, fullText, team, country, urlPlayer);
        return "redirect:/players";
    }

    //Страница конкретного Игрока
    @GetMapping("/players/{id}")
    public String playerDetails(@PathVariable(value = "id") Long id, Model model) {
        if (!playerServise.existsPlayer(id)) {
            return "redirect:/players";
        }
        playerServise.getInfoByPlayers(id, model);
        reviewServise.getModelReviews(id, model);
        return "playerPackage/players-details";
    }

    @PostMapping("/players/{id}")
    public String playerPostReview(@PathVariable(value = "id") Long id,
                                   @RequestParam String anons,
                                   @RequestParam String fullReview,
                                   Model model) {
        reviewServise.saveReviewsPlayer(anons, fullReview, id);
        return "redirect:/players";
    }

    //Получение данных об Игроке для его дальнейшего редактирования
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/players/{id}edit")
    public String playerEdit(@PathVariable(value = "id") Long id, Model model) {
        if (!playerServise.existsPlayer(id))  {
            return "redirect:/players";
        }
        playerServise.getInfoByPlayers(id, model);
        teamServise.getModelTeams(model);
        countryServise.getModelCount(model);
        return "playerPackage/players-edit";
    }

    //Редактирование данных Игрока
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/players/{id}edit")
    public String playerPostUbdate(@PathVariable(value = "id") Long id,
                                   @RequestParam String name,
                                   @RequestParam Team team,
                                   @RequestParam(value = "country", required = false) Country country,
                                   @RequestParam String nickname,
                                   @RequestParam String fullText,
                                   @RequestParam(value = "urlPlayer", required = false) String urlPlayer,
                                   Model model) {
        playerServise.editPlayerToDB(id, name, nickname, fullText, team, country, urlPlayer);
        return "redirect:/players";
    }

    //Удаление Игрока
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/players/{id}remove")
    public String playerPostDelete(@PathVariable(value = "id") Long id, Model model) {
        playerServise.deletePlayerOnDB(id);
        return "redirect:/players";
    }
}
