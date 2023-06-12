package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "coach")
public class Coach  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //connection with club
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    //connection with reviews
    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY)

    private List<Review> reviews;

    private String nameCoach;

    private LocalDateTime dob;

    private String descriptCoach;

    @Column(name="urlCoach", length = 255)
    private String urlCoach;

    private Boolean isConfirmed;

    public Coach() {
    }
    public void info(){};
    public Coach(String nameCoach, String descriptCoach, Team team, String urlCoach) {
        this.nameCoach = nameCoach;
//        this.dob = dob;
        this.descriptCoach = descriptCoach;
        this.team = team;
        this.urlCoach = urlCoach;
        this.isConfirmed = false;
    }

}
