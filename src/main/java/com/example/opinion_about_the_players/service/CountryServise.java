package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CountryServise {

    @Autowired
    CountryRepository countryRepository;

    public Model getCountries(Model model){
        Iterable<Country> countries= countryRepository.findAll();
        return model.addAttribute("countries",countries);
    }

     public  void saveCountryToDB(String nameCountry) {
         if (!nameCountry.equals("")) {
             Country country = new Country();
             country.setNameСountry(nameCountry);
             countryRepository.save(country);
         }
     }
}
