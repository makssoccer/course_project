package com.example.opinion_about_the_players.repository;


import com.example.opinion_about_the_players.models.Tournament;
import org.springframework.data.repository.CrudRepository;

public interface TournamentRepository extends CrudRepository<Tournament,Long> {
}
