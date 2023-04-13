package com.example.opinion_about_the_players.service;

import com.example.opinion_about_the_players.models.Role;
import com.example.opinion_about_the_players.models.User;
import com.example.opinion_about_the_players.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Model getModelUsers(Model model) {
        List<User> users = userRepository.findAll();
        return model.addAttribute("users", users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Boolean active, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        user.setActive(active);
        userRepository.save(user);
    }

    public void userTurnOffActive(User user) {
        user.setActive(false);
        userRepository.save(user);
    }

    public void updateAccount(User user, String email, String password) {
        String userEmail = user.getEmail();

        boolean isEmailWasChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailWasChanged) {
            user.setEmail(email);
           /*if(!StringUtils.isEmpty(email)){
               user.set;   //aктивация по почте
           }*/
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        userRepository.save(user);
    }
}