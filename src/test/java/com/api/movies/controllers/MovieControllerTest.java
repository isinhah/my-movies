package com.api.movies.controllers;

import com.api.movies.domain.Movie;
import com.api.movies.dtos.movies.MoviePostRequestBodyDTO;
import com.api.movies.dtos.movies.MoviePutRequestBodyDTO;
import com.api.movies.exceptions.NotFoundException;
import com.api.movies.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a page of movies")
    void listAll() {
        Pageable pageable = mock(Pageable.class);

        when(movieService.listAll(pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));

        movieController.listAll(pageable);

        verify(movieService, times(1)).listAll(pageable);
    }

    @Test
    @DisplayName("Should return all movies")
    void getAll() {
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        when(movieService.getAll()).thenReturn(movies);

        List<Movie> result = movieController.getAll().getBody();

        verify(movieService, times(1)).getAll();

        assertEquals(movies, result);
    }

    @Test
    @DisplayName("Should return a movie by ID or throw NotFoundException")
    void getByIdOrThrowNotFoundException() {
        Long movieId = 1L;
        Movie movie = new Movie();
        movie.setId(movieId);

        when(movieService.getByIdOrThrowNotFoundException(movieId)).thenReturn(movie);

        ResponseEntity<Movie> response = movieController.getById(movieId);

        verify(movieService, times(1)).getByIdOrThrowNotFoundException(movieId);

        assertEquals(movie, response.getBody());
    }

    @Test
    @DisplayName("Should return a list of movies by title or throw NotFoundException")
    void getByTitle() {
        String title = "Inception";
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());

        when(movieService.getByTitle(title)).thenReturn(movies);

        ResponseEntity<List<Movie>> response = movieController.getByTitle(title);

        verify(movieService, times(1)).getByTitle(title);

        assertEquals(movies, response.getBody());
    }

    @Test
    @DisplayName("Should return a list of movies by rating or throw NotFoundException")
    void getByRating() {
        Integer rating = 5;
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());

        when(movieService.getByRating(rating)).thenReturn(movies);

        ResponseEntity<List<Movie>> response = movieController.getByRating(rating);

        verify(movieService, times(1)).getByRating(rating);

        assertEquals(movies, response.getBody());
    }

    @Test
    @DisplayName("Should return a list of movies by review or throw NotFoundException")
    void getByReview() {
        String review = "Ótimo filme";
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());

        when(movieService.getByReview(review)).thenReturn(movies);

        ResponseEntity<List<Movie>> response = movieController.getByReview(review);

        verify(movieService, times(1)).getByReview(review);

        assertEquals(movies, response.getBody());
    }

    @Test
    @DisplayName("Should save a movie")
    void save() {
        MoviePostRequestBodyDTO movieDTO = new MoviePostRequestBodyDTO("Inception", 5, "Ótimo filme");

        Movie savedMovie = new Movie();
        savedMovie.setId(1L);
        savedMovie.setTitle(movieDTO.getTitle());
        savedMovie.setRating(movieDTO.getRating());
        savedMovie.setReview(movieDTO.getReview());

        when(movieService.save(movieDTO)).thenReturn(savedMovie);

        ResponseEntity<Movie> response = movieController.save(movieDTO);

        verify(movieService, times(1)).save(movieDTO);

        assertEquals(savedMovie, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Should replace a movie or throw NotFoundException")
    void replaceSuccess() {
        MoviePutRequestBodyDTO movieDTO = new MoviePutRequestBodyDTO();
        movieDTO.setId(1L);
        movieDTO.setTitle("Inception");
        movieDTO.setRating(5);
        movieDTO.setReview("Ótimo filme");

        ResponseEntity<Void> response = movieController.replace(movieDTO);

        verify(movieService, times(1)).replace(movieDTO);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Should throw NotFoundException when replacing a movie that does not exist")
    void replaceError() {
        MoviePutRequestBodyDTO movieDTO = new MoviePutRequestBodyDTO();
        movieDTO.setId(1L);
        movieDTO.setTitle("Inception");
        movieDTO.setRating(5);
        movieDTO.setReview("Ótimo filme");

        doThrow(NotFoundException.class).when(movieService).replace(movieDTO);

        assertThrows(NotFoundException.class, () -> movieController.replace(movieDTO));

        verify(movieService, times(1)).replace(movieDTO);
    }

    @Test
    @DisplayName("Should delete a movie by ID or throw NotFoundException")
    void deleteSuccess() {
        Long movieId = 1L;

        ResponseEntity<Void> response = movieController.delete(movieId);

        verify(movieService, times(1)).delete(movieId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting a movie that does not exist")
    void deleteError() {
        Long movieId = 1L;

        doThrow(NotFoundException.class).when(movieService).delete(movieId);

        assertThrows(NotFoundException.class, () -> movieController.delete(movieId));

        verify(movieService, times(1)).delete(movieId);
    }
}