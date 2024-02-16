package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT mv FROM Movie mv " +
            "JOIN FETCH mv.genre " +
            "WHERE (:genreId IS NULL OR mv.genre.id = :genreId)")
    Page<Movie> searchByGenre(Long genreId, Pageable pageable);
}
