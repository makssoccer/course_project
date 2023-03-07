package com.example.opinion_about_the_players.conrtrollers;

import com.example.opinion_about_the_players.models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("title", "Главная страница");

        return "home";
    }
    @GetMapping("/main")
    public String main( Model model) {
        model.addAttribute("title", "Главная страница");

        return "main";
    }

}