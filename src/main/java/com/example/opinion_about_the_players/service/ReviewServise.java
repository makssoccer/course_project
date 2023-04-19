package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Coach;
import com.example.opinion_about_the_players.models.Player;
import com.example.opinion_about_the_players.models.Review;
import com.example.opinion_about_the_players.models.User;
import com.example.opinion_about_the_players.repository.CoachRepository;
import com.example.opinion_about_the_players.repository.PlayerRepository;
import com.example.opinion_about_the_players.repository.ReviewRepositiry;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServise {

    private final ReviewRepositiry reviewRepository;

    private final PlayerRepository playerRepository;

    private final CoachRepository coachRepository;
    @Transactional
    public Model getReviews(Model model) {
        List<Review> reviews = reviewRepository.findAll();
        return model.addAttribute("reviews", reviews);
    }
    @Transactional
    public Model getModelReviews(Long id, Model model) {
        List<Review> reviews = reviewRepository.getByNameWithPlayer(id);
        return model.addAttribute("reviews", reviews);
    }
    @Transactional
    public void saveReviewsPlayer(String anons, String fullReviews, Long id) {
        User user = getUserSession();
        LocalDateTime current = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        Player player = playerRepository.getPlayerW(id);
        Review review = new Review(player, user, anons, fullReviews, current);
        reviewRepository.save(review);
    }

    @Transactional
    public void saveReviewsCoach(String anons, String fullReviews, Long id) {
        User user = getUserSession();
        LocalDateTime current = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        Coach coach = coachRepository.getCoachW(id);
        Review review = new Review(coach, user, anons, fullReviews, current);
        reviewRepository.save(review);
    }

    private User getUserSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }


}
