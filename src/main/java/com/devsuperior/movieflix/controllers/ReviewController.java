package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService reviewService) {
        this.service = reviewService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_MEMBER')")
    public ReviewDTO insert(@Valid @RequestBody ReviewDTO dto) {
        return service.insert(dto);
    }
}
