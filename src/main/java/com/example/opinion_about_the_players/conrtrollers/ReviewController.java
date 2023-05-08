package com.example.opinion_about_the_players.conrtrollers;

import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.Player;
import com.example.opinion_about_the_players.models.Team;
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
public class ReviewController {

        private final PlayerServise playerServise;
        private final TeamServise teamServise;
        private final CountryServise countryServise;
        private final ReviewServise reviewServise;
        private final UserService userService;



//    @GetMapping("/review/{id}")
//    public String getReview(@PathVariable(value = "id") Long id, Model model) {
//        if (!playerServise.existsPlayer(id)) {
//            return "redirect:/players";
//        }
//        playerServise.getInfoByPlayers(id, model);
//        reviewServise.getModelReviews(id, model);
//        return "reviewPackage/review";
//    }
//
//    @PostMapping("/review/{id}")
//    public String postReview(@PathVariable(value = "id") Long id,
//                             @RequestParam String anons,
//                             @RequestParam String fullReview,
//                             Model model) {
//        reviewServise.saveReviewsPlayer(anons, fullReview, id);
//        return "redirect:/playerPackage/players-details";
//    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/review")
    public String playerAdd(Model model) {
        playerServise.getModelPlayers( model);

        return "reviewPackage/review";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PostMapping("/review")
    public String playerPostAdd(@RequestParam(value = "player") Player player,
                                @RequestParam String anons,
                                @RequestParam String fullReview) {
        reviewServise.newSaveReviewsPlayer(anons, fullReview, player);
        return "redirect:/review";
    }
}
