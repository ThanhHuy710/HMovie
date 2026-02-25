package com.example.HMovie.repository;

import com.example.HMovie.dto.response.MovieResponse;
import com.example.HMovie.entity.Movie;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    @EntityGraph(attributePaths = {"episodes"})
    Optional<Movie> findById(String movieId);
    List<Movie> findByIsSeries(Boolean isSeries);
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByDirectorContainingIgnoreCase(String director);
    List<Movie> findByActorContainingIgnoreCase(String actor);
    List<Movie> findByYear(Integer year);
    List<Movie> findByCountry(String country);
    List<Movie> findAllByOrderByViewCountDesc();
    List<Movie> findAllByOrderByAverageRatingDesc();
    List<Movie> findAllByOrderByFavoriteCountDesc();
    @Query("SELECT m FROM Movie m, MovieGenre mg WHERE m = mg.movie AND mg.genre.name = :genreName")
    List<Movie> findMoviesByGenreName(@Param("genreName") String genreName);
    List<Movie> findByOriginalNameOrderBySeasonAsc(String originalName);
    @Query("SELECT f.movie FROM Favorite f WHERE f.profile.profileId = :profileId ORDER BY f.addedAt DESC")
    List<Movie> findFavoriteMoviesByProfileId(@Param("profileId") String profileId);
    @Query("SELECT v.movie FROM View v WHERE v.profile.profileId = :profileId ORDER BY v.viewedAt DESC")
    List<Movie> findHistoryMoviesByProfileId(String profileId);
    @Query("SELECT m.actor FROM Movie m WHERE LOWER(m.actor) LIKE LOWER(CONCAT('%', :actor, '%'))")
    List<String> findActorStringsByActor(@Param("actor") String actor);
    @Query("SELECT m.director FROM Movie m WHERE LOWER(m.director) LIKE LOWER(CONCAT('%', :director, '%'))")
    List<String> findDirectorStringsByDirector(@Param("director") String director);

    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.averageRating = (SELECT COALESCE(AVG(f.rating), 0.0) FROM Feedback f WHERE f.movie.movieId = :movieId) WHERE m.movieId = :movieId")
    void calculateAverageRatingByMovieId(@Param("movieId") String movieId);

    @Query("SELECT DISTINCT m FROM Movie m " +
            "LEFT JOIN m.movieGenres mg " +
            "WHERE (:countries IS NULL OR m.country IN :countries) " +
            "AND (:isSeries IS NULL OR m.isSeries = :isSeries) " +
            "AND (:year IS NULL OR m.year = :year) " +
            "AND (:ageRating IS NULL OR m.ageRating = :ageRating) " +
            "AND (:genres IS NULL OR mg.genre.name IN :genres)")
    List<Movie> findMoviesByCriteria(
            @Param("countries") String countries,
            @Param("isSeries") Boolean isSeries,
            @Param("year") Integer year,
            @Param("ageRating") String ageRating,
            @Param("genres") List<String> genres
    );
}
