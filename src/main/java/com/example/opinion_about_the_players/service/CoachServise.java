package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Player;
import com.example.opinion_about_the_players.models.Team;
import com.example.opinion_about_the_players.models.Coach;
import com.example.opinion_about_the_players.repository.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CoachServise {

    private final CoachRepository coachRepository;

    @Transactional
    public Model getModelCoaches(Model model) {
        List<Coach> coaches = coachRepository.getConfirmedCoaches();
        return model.addAttribute("coaches", coaches);
    }
    @Transactional
    public Model getNoApproveCoaches(Model model) {
        List<Coach> coaches = coachRepository.getNoConfirmedCoaches();
        return model.addAttribute("coaches", coaches);
    }
    @Transactional
    public void saveCoach(String nameCoach, String descriptCoach, Team team, String urlCoach) {
        Coach coach = new Coach(nameCoach, descriptCoach, team, urlCoach);
        coachRepository.save(coach);
    }
    @Transactional
    public boolean existsCoach(Long id) {
        return coachRepository.existsById(id);
    }
    @Transactional
    public Model getInfoByCoaches(Long id, Model model) {

        Optional<Coach> coach = coachRepository.findById(id);
        List<Coach> res = new ArrayList<>();
        coach.ifPresent(res::add);
        return model.addAttribute("coach", res);
    }
    @Transactional
    public void editCoachToDB(Long id, String nameCoach, String descriptCoach, Team team,String urlCoach) {
        coachRepository.updateCoach(nameCoach, descriptCoach, team, urlCoach, id);
    }
    @Transactional
    public void deleteСoachOnDB(Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow();
        coachRepository.delete(coach);
    }
}
