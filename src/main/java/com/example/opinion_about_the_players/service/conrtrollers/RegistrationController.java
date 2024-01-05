package com.example.opinion_about_the_players.service.conrtrollers;


import com.example.opinion_about_the_players.models.Role;
import com.example.opinion_about_the_players.models.User;
import com.example.opinion_about_the_players.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

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
        User userFromdb = userService.findUser(user);
        if (userFromdb != null) {
            model.addAttribute("message", "User exists!");
            model.addAttribute("title", "Error login");
            return "error-register";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.savePlayer(user);
        model.addAttribute("message", "New account!");
        model.addAttribute("title", "New account");
        return "redirect:/login";
    }
}