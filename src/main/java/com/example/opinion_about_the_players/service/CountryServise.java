package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
    @Transactional
    public void saveCountry(String nameCountry, String urlCountry) {
        if (!nameCountry.isEmpty()) {
            Country country = new Country(nameCountry);
            country.setUrlCountry(urlCountry);
            countryRepository.save(country);
        }
    }

    public void Scrape(){
        final String url="https://www.worldometers.info/geography/flags-of-the-world";
        try {
            final Document document = Jsoup.connect(url).get();
            Elements body = document.select("div.row div.col-md-4");
            for(Element e: body) {
                String nameCountry = e.text();
                StringBuilder next_link = new StringBuilder("https://www.worldometers.info");
                next_link.append(e.select("a").attr("href"));
                String urlContry = String.valueOf(next_link);
                Country country = new Country(nameCountry,urlContry);
                countryRepository.save(country);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();

        }
    }
}
