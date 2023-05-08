package com.example.opinion_about_the_players.repository;

import com.example.opinion_about_the_players.models.Coach;
import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.Player;
import com.example.opinion_about_the_players.models.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach,Long> {

    @Query("select c from Coach c where c.isConfirmed = true")
    List<Coach> getConfirmedCoaches();
    @Query("select c from Coach c where c.isConfirmed = false ")
    List<Coach> getNoConfirmedCoaches();
    @EntityGraph(attributePaths = {"reviews"})
    @Query("select c from Coach c where c.id = ?1")
    Coach getCoachW(long id);

    @Modifying
    @Query("update Coach c set  c.nameCoach = :nameCoach,  c.descriptCoach = :descriptCoach, " +
            "c.team = :team,  c.urlCoach = :urlCoach   where c.id = :id")
    void updateCoach(@Param("nameCoach")String nameCoach,
                     @Param("descriptCoach")String descriptCoach,
                     @Param("team") Team team,
                     @Param("urlCoach") String urlCoach,
                     @Param("id") Long id);
}


