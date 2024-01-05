package com.example.opinion_about_the_players.repository;

import com.example.opinion_about_the_players.models.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
//    @EntityGraph(attributePaths = "review")
//    @Query("select c from Commentary c where c.id = ?1")
//    List<Commentary> getCommentaryByIdReview(Long id);

//    @Query("select r from Commentary r where r.id = ?1")
//    Commentary getCommentaryById(long id);
}
