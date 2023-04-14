package com.example.opinion_about_the_players.repository;

import com.example.opinion_about_the_players.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {

}