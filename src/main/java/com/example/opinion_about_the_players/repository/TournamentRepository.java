package com.example.opinion_about_the_players.repository;


import com.example.opinion_about_the_players.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {
}
