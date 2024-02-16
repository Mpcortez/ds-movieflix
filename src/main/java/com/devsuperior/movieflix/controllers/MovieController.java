package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    public MovieDetailsDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    public Page<MovieCardDTO> findAllPageable(@RequestParam(value = "genreId", required = false) Long genreId,
                                              @PageableDefault(sort = "title") Pageable pageable) {
        return service.findAllPageable(genreId, pageable);
    }
}
