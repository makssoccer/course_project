package com.example.opinion_about_the_players.repository;

import com.example.opinion_about_the_players.models.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepositiry extends CrudRepository<Review,Long> {
        @EntityGraph(attributePaths = "player")
    @Query("select r from Review r where r.id = ?1")
        Iterable< Review> getByNameWithPlayer(Long id);
}
