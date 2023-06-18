package com.example.opinion_about_the_players.conrtrollers;

import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.Tournament;
import com.example.opinion_about_the_players.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {


    private final PlayerServise playerServise;
    private final TeamServise teamServise;
    private final UserService userService;
    private final CoachServise coachServise;
    private final TournamentServise tournamentServise;
    private final CountryServise countryServise;


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/addCountry")
    public String playerPostAdd() {
        //парсинг Стран
        countryServise.Scrape();
        return "redirect:/players";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin")
    public String playerMain(Model model) {
        coachServise.getNoApproveCoaches(model);
        playerServise.getNoApprovePlayers(model);
        teamServise.getNoApproveTeams(model);
        userService.getModelUsers(model);
        tournamentServise.getModelTournaments(model);
        countryServise.getModelCount(model);
        return "admin";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/admin")
    public String teamPostAdd(@RequestParam(value = "country", required = false) Country country,
                              @RequestParam(value = "nameCountry", required = false) String nameCountry,
                              @RequestParam(value = "urlCountry", required = false) String urlCountry,
                              @RequestParam(value = "nameTournament", required = false) String nameTournament) {
        tournamentServise.saveTournament(nameTournament, country);
        countryServise.saveCountry(nameCountry,urlCountry);
        return "redirect:/teams";
    }
}
