package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "coach")
public class Coach {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    ////connection with club
    @ManyToMany(mappedBy="tournament", fetch = FetchType.LAZY)
    private List<Team> team;

    ////connection with reviews
    @OneToMany(mappedBy = "coach", fetch = FetchType.EAGER)
    private List<Review> reviews;


    private String name;
    private String surname;
    private LocalDateTime dob;
    private String descriptionsCoach;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviewss(List<Review> reviews) {
        this.reviews = reviews;
    }


    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDescriptionsCoach() {
        return descriptionsCoach;
    }

    public void setDescriptionsCoach(String full_text) {
        this.descriptionsCoach = full_text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coach() {
    }
    public Coach(String name, String fullText, List<Team> teams, Country country, LocalDateTime dob, String surname) {
        this.name = name;
        this.surname=surname;
        this.dob=dob;
        this.descriptionsCoach = fullText;
        this.team=teams;

    }

}
