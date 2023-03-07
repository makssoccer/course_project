package com.example.opinion_about_the_players.repository;

import com.example.opinion_about_the_players.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
