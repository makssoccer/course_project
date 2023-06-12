package com.example.opinion_about_the_players.repository;

import com.example.opinion_about_the_players.models.Coach;
import com.example.opinion_about_the_players.models.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepositiry extends JpaRepository<Review, Long> {
    @EntityGraph(attributePaths = "player")
    @Query("select r from Review r where r.id = ?1")
    List<Review> getByNameWithPlayer(Long id);

    @EntityGraph(attributePaths = "coach")
    @Query("select r from Review r where r.id = ?1")
    List<Review> getByNameWithCoach(Long id);

    @Query("select r from Review r where r.id = ?1")
    Review getReviewById(long id);
}
