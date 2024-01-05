package com.example.opinion_about_the_players.repository;

import com.example.opinion_about_the_players.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {
    @Query("select t from Team t where t.isConfirmed = true")
    List<Team> getConfirmedTeams();

    @Query("select t from Team t where t.isConfirmed = false ")
    List<Team> getNoConfirmedTeams();

}