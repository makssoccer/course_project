package com.example.opinion_about_the_players.repository;


import com.example.opinion_about_the_players.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
