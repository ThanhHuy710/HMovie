package com.example.HMovie.repository;

import com.example.HMovie.entity.View;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewRepository extends JpaRepository<View, String> {
    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.viewCount = m.viewCount + 1 WHERE m.movieId = :movieId ")
    void calculateTotalViewCountByMovieId(@Param("movieId") String movieId);
    List<View> findByProfileProfileId(String profileId);
}
