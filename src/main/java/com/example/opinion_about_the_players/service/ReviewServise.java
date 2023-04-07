package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Player;
import com.example.opinion_about_the_players.models.Review;
import com.example.opinion_about_the_players.models.User;
import com.example.opinion_about_the_players.repository.PlayerRepository;
import com.example.opinion_about_the_players.repository.ReviewRepositiry;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ReviewServise {

    @Autowired
    ReviewRepositiry reviewRepository;
    @Autowired
    private PlayerRepository playerRepository;
    public Model getReviews(Model model){
        Iterable<Review> reviews= reviewRepository.findAll();
        return model.addAttribute("reviews",reviews);
    }
    public Model getPlayerReviews(long id,Model model){
        Iterable<Review> reviews= reviewRepository.getByNameWithPlayer(id);
        return model.addAttribute("reviews",reviews);
    }

    public void saveRiviewsPlayer(String anons, String fullReviews, long id ) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Review review = new Review();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime current = LocalDateTime.of(date, time);
        Player player= playerRepository.findById(id).orElseThrow();
        review.setAnons(anons);
        review.setFullRewiew(fullReviews);
        review.setPlayer(player);
        review.setTimePost(current);
        review.setUser(user);

        reviewRepository.save(review);
    }
}
