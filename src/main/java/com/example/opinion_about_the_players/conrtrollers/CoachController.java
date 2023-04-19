package com.example.opinion_about_the_players.conrtrollers;

import com.example.opinion_about_the_players.models.Team;
import com.example.opinion_about_the_players.service.CoachServise;
import com.example.opinion_about_the_players.service.ReviewServise;
import com.example.opinion_about_the_players.service.TeamServise;
import com.example.opinion_about_the_players.service.UserService;
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
public class CoachController {

    private final CoachServise coachServise;
    private final TeamServise teamServise;
    private final ReviewServise reviewServise;
    private final UserService userService;

    @GetMapping("/coaches")
    public String coachMain(Model model) {
        coachServise.getModelCoaches(model);
        userService.getModelUsers(model);
        return "coachPackage/coaches";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/coaches/add")
    public String coachAdd(Model model) {
        teamServise.getModelTeams(model);

        return "coachPackage/coaches-add";

    }

    //create coach
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/coaches/add")
    public String coachPostAdd(@RequestParam String nameCoach,
                                @RequestParam String descriptCoach,
                                @RequestParam(value = "team", required = false) Team team,
                                @RequestParam(value = "urlCoach", required = false) String urlCoach,
                                Model model) {
        coachServise.saveCoach(nameCoach,  descriptCoach, team, urlCoach);
        return "redirect:/coaches";
    }

    //Страница конкретного Игрока
    @GetMapping("/coaches/{id}")
    public String coachDetails(@PathVariable(value = "id") Long id, Model model) {
        if (!coachServise.existsCoach(id)) {
            return "redirect:/coaches";
        }
        coachServise.getInfoByCoaches(id, model);
        reviewServise.getModelReviews(id, model);
        return "coachPackage/coaches-details";
    }

    @PostMapping("/coaches/{id}")
    public String coachPostReview(@PathVariable(value = "id") Long id,
                                   @RequestParam String anons,
                                   @RequestParam String fullReview,
                                   Model model) {
        reviewServise.saveReviewsCoach(anons, fullReview, id);
        return "redirect:/coaches";
    }

    //Получение данных об Игроке для его дальнейшего редактирования
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/coaches/{id}edit")
    public String coachEdit(@PathVariable(value = "id") Long id, Model model) {
        if (!coachServise.existsCoach(id))  {
            return "redirect:/coaches";
        }
        coachServise.getInfoByCoaches(id, model);
        teamServise.getModelTeams(model);
        return "coachPackage/coaches-edit";
    }

    //Редактирование данных Тренера
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/coaches/{id}edit")
    public String coachPostUbdate(@PathVariable(value = "id") Long id,
                                  @RequestParam String nameCoach,
                                  @RequestParam String descriptCoach,
                                  @RequestParam(value = "team", required = false) Team team,
                                  @RequestParam(value = "urlCoach", required = false) String urlCoach,
                                   Model model) {
        coachServise.editCoachToDB(id, nameCoach, descriptCoach, team,  urlCoach);
        return "redirect:/coaches";
    }

    //Удаление Тренера
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/coaches/{id}remove")
    public String coachPostDelete(@PathVariable(value = "id") Long id, Model model) {
        coachServise.deleteСoachOnDB(id);
        return "redirect:/coaches";
    }
}


