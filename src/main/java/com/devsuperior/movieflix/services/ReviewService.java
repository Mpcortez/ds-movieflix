package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    private final MovieService movieService;

    private final UserService userService;

    public ReviewService(ReviewRepository repository, MovieService movieService, UserService userService) {
        this.repository = repository;
        this.movieService = movieService;
        this.userService = userService;
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review review = new Review();
        copiarDtoParaEntidade(review, dto);
        repository.save(review);
        return new ReviewDTO(review);
    }

    protected void copiarDtoParaEntidade(Review review, ReviewDTO reviewDTO) {
        Movie movie = movieService.getReferenceById(reviewDTO.getMovieId());
        UserDTO userDTO = userService.getProfile();

        review.setText(reviewDTO.getText());
        review.setMovie(movie);
        review.setUser(new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail()));
    }
}
