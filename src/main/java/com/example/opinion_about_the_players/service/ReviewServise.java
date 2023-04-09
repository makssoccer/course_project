package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Player;
import com.example.opinion_about_the_players.models.Review;
import com.example.opinion_about_the_players.models.User;
import com.example.opinion_about_the_players.repository.PlayerRepository;
import com.example.opinion_about_the_players.repository.ReviewRepositiry;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class ReviewServise {

    private final ReviewRepositiry reviewRepository;

    private final PlayerRepository playerRepository;

    public Model getReviews(Model model) {
        Iterable<Review> reviews = reviewRepository.findAll();
        return model.addAttribute("reviews", reviews);
    }

    public Model getModelReviews(long id, Model model) {
        Iterable<Review> reviews = reviewRepository.getByNameWithPlayer(id);
        return model.addAttribute("reviews", reviews);
    }

    public void saveRiviewsPlayer(String anons, String fullReviews, long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        LocalDateTime current = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        Player player = playerRepository.findById(id).orElseThrow();
        Review review = new Review(player, user, anons, fullReviews, current);
        reviewRepository.save(review);
    }
}
