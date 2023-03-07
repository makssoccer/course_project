package com.example.opinion_about_the_players.conrtrollers;


import com.example.opinion_about_the_players.models.Role;
import com.example.opinion_about_the_players.models.User;
import com.example.opinion_about_the_players.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Регистрация");
        return "registration";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Авторизация");
        return "login";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromdb = userRepository.findByUsername(user.getUsername());
        if (userFromdb != null) {
            model.addAttribute("message", "User exists!");
            model.addAttribute("title", "Error login");
            return "error-register";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        model.addAttribute("message", "New account!");
        model.addAttribute("title", "New account");
        return "redirect:/login";
    }
}