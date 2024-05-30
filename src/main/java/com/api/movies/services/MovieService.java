package com.api.movies.services;

import com.api.movies.domain.Movie;
import com.api.movies.dtos.movies.MoviePostRequestBodyDTO;
import com.api.movies.dtos.movies.MoviePutRequestBodyDTO;
import com.api.movies.exceptions.BadRequestException;
import com.api.movies.exceptions.NotFoundException;
import com.api.movies.mappers.MovieMapper;
import com.api.movies.repositories.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Page<Movie> listAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Movie getByIdOrThrowNotFoundException(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie not found."));
    }

    public List<Movie> getByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        if (movies.isEmpty()) {
            throw new NotFoundException("No movies found with this title.");
        }
        return movies;
    }

    public List<Movie> getByRating(Integer rating) {
        List<Movie> movies = movieRepository.findByRating(rating);
        if (movies.isEmpty()) {
            throw new NotFoundException("No movies found with this rating.");
        }
        return movies;
    }

    public List<Movie> getByReview(String review) {
        List<Movie> movies = movieRepository.findByReviewContainingIgnoreCase(review);
        if (movies.isEmpty()) {
            throw new NotFoundException("No movies found with this review.");
        }
        return movies;
    }

    @Transactional
    public Movie save(MoviePostRequestBodyDTO moviePostRequestBodyDTO) {
        Movie movie = MovieMapper.toEntityMovie(moviePostRequestBodyDTO);
        return movieRepository.save(movie);
    }

    @Transactional
    public void replace(MoviePutRequestBodyDTO moviePutRequestBodyDTO) {
        Movie savedAnime = getByIdOrThrowNotFoundException(moviePutRequestBodyDTO.getId());
        Movie movie = MovieMapper.toEntityMovie(moviePutRequestBodyDTO);
        movie.setId(savedAnime.getId());
        movieRepository.save(movie);
    }

    @Transactional
    public void delete(Long id) {
       movieRepository.delete(getByIdOrThrowNotFoundException(id));
    }
}