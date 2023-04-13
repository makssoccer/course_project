package com.example.opinion_about_the_players.repository;


import com.example.opinion_about_the_players.models.Country;
import com.example.opinion_about_the_players.models.Player;
import com.example.opinion_about_the_players.models.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PlayerRepository extends JpaRepository<Player,Long> {

    @EntityGraph(attributePaths = {"country", "reviews"})
    @Query("select p from Player p where p.id = ?1")
    Player getPlayerW(long id);


    @Modifying
    @Query("update Player p set  p.name = :name, p.nickname = :nickname, p.fullText = :fullText, " +
            "p.team = :team, p.country = :country where p.id = :id")
    void updatePlayer(@Param("name")String name,
                      @Param("nickname")String nickname,
                      @Param("fullText")String fullText,
                      @Param("team")Team team,
                      @Param("country")Country country,
                      @Param("id") Long id);
    Player findByCountryAndNicknameAndName(Country country, String nik, String name);
}
