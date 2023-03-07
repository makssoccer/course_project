package com.example.opinion_about_the_players.repository;


import com.example.opinion_about_the_players.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country,Long> {
}
