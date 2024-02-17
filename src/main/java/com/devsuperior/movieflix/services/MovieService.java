package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Movie movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        return new MovieDetailsDTO(movie);
    }

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findAllPageable(Long genreId, Pageable pageable) {
        return repository.searchByGenre(genreId, pageable).map(MovieCardDTO::new);
    }

    @Transactional(readOnly = true)
    public Movie getReferenceById(Long movieId) {
        try {
            return repository.getReferenceById(movieId);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found.");
        }
    }
}
