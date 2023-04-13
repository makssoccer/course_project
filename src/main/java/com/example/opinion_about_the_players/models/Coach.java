package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "coach")
public class Coach {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //connection with club
    @ManyToMany(mappedBy = "tournament", fetch = FetchType.LAZY)
    private List<Team> team;

    //connection with reviews
    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY)

    private List<Review> reviews;

    private String name;

    private LocalDateTime dob;

    private String descriptionsCoach;

    public Coach() {
    }

    public Coach(String name, String fullText, List<Team> teams, Country country, LocalDateTime dob) {
        this.name = name;
        this.dob = dob;
        this.descriptionsCoach = fullText;
        this.team = teams;
    }

}
