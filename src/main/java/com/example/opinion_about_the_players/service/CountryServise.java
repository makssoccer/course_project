package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CountryServise {

    private final CountryRepository countryRepository;
@Transactional
    public Model getModelCount(Model model) {
        List<Country> countries = countryRepository.findAll();
        return model.addAttribute("countries", countries);
    }

    public void saveCountry(String nameCountry) {
        if (!nameCountry.isEmpty()) {
            Country country = new Country(nameCountry);
            countryRepository.save(country);
        }
    }
}
