package com.example.opinion_about_the_players.conrtrollers;

import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.Tournament;
import com.example.opinion_about_the_players.service.TeamServise;
import com.example.opinion_about_the_players.service.CountryServise;
import com.example.opinion_about_the_players.service.TournamentServise;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PageController {
    private final TeamServise teamServise;
    private final TournamentServise tournamentServise;
    private final CountryServise countryServise;


    //Вывод всех клубов на экран
    @GetMapping("/teams")
    public String teamMain(Model model) {
        teamServise.getModelTeams(model);
        return "teamPackage/teams";
    }

    //получаем все турниры
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/teams/add")
    public String teamAdd(Model model) {
        tournamentServise.getModelTournaments(model);
        countryServise.getModelCount(model);
//        парсинг Стран
        countryServise.Scrape();
        return "teamPackage/teams-add";

    }

    //Добавление клуба, лиги и страну
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/teams/add")
    public String teamPostAdd(@RequestParam(value = "nameTeam", required = false) String nameTeam,
                              @RequestParam(value = "tournament", required = false) List<Tournament> tournament,
                              @RequestParam(value = "country", required = false) Country country,
                              @RequestParam(value = "nameCountry", required = false) String nameCountry,
                              @RequestParam(value = "nameTournament", required = false) String nameTournament,
                              @RequestParam(value = "urlTeam", required = false) String urlTeam) {
        tournamentServise.saveTournament(nameTournament, country);
        countryServise.saveCountry(nameCountry);
        teamServise.saveTeam(nameTeam, tournament,urlTeam);
        return "redirect:/teams";
    }

    //Получаем информацию

    @GetMapping("/teams/{id}")
    public String teamDetails(@PathVariable(value = "id") Long id, Model model) {
        if (!teamServise.existsTeam(id)) {
            return "redirect:/teams";
        }
        tournamentServise.getModelTournaments(model);
        teamServise.getInfoByTeams(id, model);
        return "teamPackage/teams-details";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/teams/{id}edit")
    public String teamEdit(@PathVariable(value = "id") Long id, Model model) {
        if (!teamServise.existsTeam(id)) {
            return "redirect:/teams";
        }
        tournamentServise.getModelTournaments(model);
        teamServise.getInfoByTeams(id, model);
        return "teamPackage/teams-edit";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/teams/{id}edit")
    public String teamPostUbdate(@PathVariable(value = "id") Long id,
                                 @RequestParam String nameTeam,
                                 @RequestParam Tournament tournament,
                                 @RequestParam(value = "urlTeam", required = false) String urlTeam) {
        teamServise.editTeam(id, nameTeam, tournament,urlTeam);
        return "redirect:/teams";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/teams/{id}remove")
    public String teamPostDelete(@PathVariable(value = "id") Long id) {
        teamServise.deleteTeamOnDB(id);
        return "redirect:/teams";
    }

}