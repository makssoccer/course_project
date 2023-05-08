package com.example.opinion_about_the_players.conrtrollers;

import com.example.opinion_about_the_players.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {


        private final PlayerServise playerServise;
        private final TeamServise teamServise;
        private final UserService userService;
        private final CoachServise coachServise;

        @GetMapping("/admin")
        public String playerMain(Model model) {
            coachServise.getNoApproveCoaches(model);
            playerServise.getNoApprovePlayers(model);
            teamServise.getNoApproveTeams(model);
            userService.getModelUsers(model);
            return "/admin";
        }
}
