package com.example.opinion_about_the_players.conrtrollers;


import com.example.opinion_about_the_players.models.Role;
import com.example.opinion_about_the_players.models.User;
import com.example.opinion_about_the_players.repository.UserRepository;
import com.example.opinion_about_the_players.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        Iterable<User> users = userService.findAll();
        model.addAttribute("users",users);
        model.addAttribute("roles", Role.values());
        return "user-list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEdit(@PathVariable User user,Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles",Role.values());
        return "user-edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{user}/remove")
    public String userDelete(@PathVariable User user, Model model) {
        user.getRoles().clear();
        userRepository.delete(user);
        model.addAttribute("user",user);
        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{user}/turnOff")
    public String userTurnOff(@RequestParam("userId") User user,Model model)
    {
        model.addAttribute("user",user);
        userService.userTurnOffActive(user);
        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Boolean active,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {

        userService.saveUser(user,username,active,form);
        return "redirect:/user";
    }

    @GetMapping("account")
    public String getAccount(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("username",user.getUsername());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("title","Личный кабинет");
        return "account";
    }

    @PostMapping("account")
    public String updateAccount(@AuthenticationPrincipal User user,
                                @RequestParam String email,
                                @RequestParam String password,
                                Model model){
        userService.updateAccount(user,email,password);
        return "redirect:/user/account";
    }
}