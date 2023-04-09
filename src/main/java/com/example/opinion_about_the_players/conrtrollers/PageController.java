package com.example.opinion_about_the_players.conrtrollers;

import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.Tournament;
import com.example.opinion_about_the_players.repository.TeamRepository;
import com.example.opinion_about_the_players.service.TeamServise;
import com.example.opinion_about_the_players.service.CountryServise;
import com.example.opinion_about_the_players.service.TournamentServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamServise teamServise;
    @Autowired
    private TournamentServise tournamentServise;
    @Autowired
    private CountryServise countryServise;


    ///////Вывод всех клубов на экран
    @GetMapping("/teams")
    public String teamMain(Model model) {
        teamServise.getModelTeams(model);
        return "teamPackage/teams";
    }

    //////получаем все турниры
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/teams/add")
    public String teamAdd(Model model) {
        tournamentServise.getModelTournaments(model);
        countryServise.getModelCount(model);
        return "teamPackage/teams-add";
    }

    ////Добавление клуба, лиги и страну
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/teams/add")
    public String teamPostAdd(@RequestParam String nameTeam, @RequestParam List<Tournament> tournament, @RequestParam Country country, @RequestParam String nameCountry, @RequestParam String nameTournament, Model model) {
        tournamentServise.saveTournamentToDB(nameTournament, country);
        countryServise.saveCountryToDB(nameCountry);
        teamServise.saveTeamToDB(nameTeam, tournament);
        return "redirect:/teams";
    }

    ////Получаем информацию
    @GetMapping("/teams/{id}")
    public String teamDetails(@PathVariable(value = "id") long id, Model model) {
        if (!teamRepository.existsById(id)) {
            return "redirect:/teams";
        }
        tournamentServise.getModelTournaments(model);
        teamServise.getInfoByTeams(id, model);
        return "teamPackage/teams-details";
    }

    @PostMapping("/teams/{id}")
    public String teamPostDetails(@PathVariable(value = "id") long id, @RequestParam String nameTeam, @RequestParam List<Tournament> tournament, Model model) {
        if (!teamRepository.existsById(id)) {
            return "redirect:/teams";
        }
        teamServise.editTeamToDB(id, nameTeam, tournament);
        return "redirect:/teams-details";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/teams/{id}edit")
    public String teamEdit(@PathVariable(value = "id") long id, Model model) {
        if (!teamRepository.existsById(id)) {
            return "redirect:/teams";
        }
        tournamentServise.getModelTournaments(model);
        teamServise.getInfoByTeams(id, model);
        return "teamPackage/teams-edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/teams/{id}edit")
    public String teamPostUbdate(@PathVariable(value = "id") long id, @RequestParam String nameTeam, @RequestParam List<Tournament> tournament, Model model) {
        teamServise.editTeamToDB(id, nameTeam, tournament);
        return "redirect:/teams";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/teams/{id}remove")
    public String teamPostDelete(@PathVariable(value = "id") long id, Model model) {
        teamServise.deleteTeamOnDB(id);
        return "redirect:/teams";
    }

}