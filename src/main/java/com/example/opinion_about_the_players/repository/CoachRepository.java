package com.example.opinion_about_the_players.repository;

import com.example.opinion_about_the_players.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Player,Long> {
}
