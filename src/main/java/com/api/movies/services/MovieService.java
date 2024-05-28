package com.api.movies.services;

import com.api.movies.domain.Movie;
import com.api.movies.dtos.movies.MoviePostRequestBodyDTO;
import com.api.movies.exceptions.BadRequestException;
import com.api.movies.mappers.MovieMapper;
import com.api.movies.repositories.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie getByIdOrThrowBadRequestException(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new BadRequestException("Movie not found"));
    }

    public List<Movie> getByTitle(String title) {
        return movieRepository.findByTitleIgnoreCase(title);
    }

    @Transactional
    public Movie save(MoviePostRequestBodyDTO moviePostRequestBodyDTO) {
        Movie movie = MovieMapper.toEntityMovie(moviePostRequestBodyDTO);
        return movieRepository.save(movie);
    }
}