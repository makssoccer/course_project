package com.example.opinion_about_the_players.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "commentary")
public class Commentary {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        //connection with review
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "review_id")
        private Review review;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User usr;


        @Column(name = "userComment")
        private String userComment;

        @Column(name = "timePost")
        private LocalDateTime timePost;


        public Commentary() {
        }

        public Commentary(Review review, User usr,  String userComment, LocalDateTime timePost) {
            this.review = review;
            this.usr = usr;
            this.userComment = userComment;
            this.timePost = timePost;
        }

}
