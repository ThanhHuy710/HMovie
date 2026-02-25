package com.example.HMovie.repository;

import com.example.HMovie.entity.MovieGenre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, String> {

    boolean existsByMovie_MovieId(String movieId);
    @Transactional
    void deleteAllByMovie_MovieId(String movieId);
}
