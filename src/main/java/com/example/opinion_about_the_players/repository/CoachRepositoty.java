package com.example.opinion_about_the_players.repository;

import com.example.opinion_about_the_players.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CoachRepositoty extends JpaRepository<Player,Long> {
}
