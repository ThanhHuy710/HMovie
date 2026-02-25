package com.example.HMovie.repository;

import com.example.HMovie.entity.Favorite;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, String> {
    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.favoriteCount = m.favoriteCount + 1 WHERE m.movieId = :movieId ")
    void calculateTotalFavoriteCountByMovieId(@Param("movieId") String movieId);
    List<Favorite> findByProfileProfileId(String profileId);
    Optional<Favorite> findByMovieMovieIdAndProfileProfileId(String movieId, String profileId);
}
