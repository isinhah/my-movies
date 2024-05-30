package com.api.movies.services;

import com.api.movies.domain.Movie;
import com.api.movies.dtos.movies.MoviePostRequestBodyDTO;
import com.api.movies.dtos.movies.MoviePutRequestBodyDTO;
import com.api.movies.exceptions.NotFoundException;
import com.api.movies.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return all movies")
    void testGetAll() {
        List<Movie> movies = new ArrayList<>();
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAll();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Should return a page of movies")
    void testListAll() {
        Page<Movie> moviePage = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        when(movieRepository.findAll(pageable)).thenReturn(moviePage);

        Page<Movie> result = movieService.listAll(pageable);

        assertNotNull(result);
        assertEquals(moviePage, result);
    }

    @Test
    @DisplayName("Should return a movie by ID or throw NotFoundException")
    void testGetByIdOrThrowNotFoundExceptionSuccess() {
        Long id = 1L;
        Movie movie = new Movie();
        when(movieRepository.findById(id)).thenReturn(Optional.of(movie));

        Movie result = movieService.getByIdOrThrowNotFoundException(id);

        assertNotNull(result);
        assertEquals(movie, result);
    }

    @Test
    @DisplayName("Should throw NotFoundException when movie is not found by ID")
    void testGetByIdOrThrowNotFoundExceptionError() {
        Long id = 1L;
        when(movieRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> movieService.getByIdOrThrowNotFoundException(id));
    }

    @Test
    @DisplayName("Should return a list of movies by title or throw NotFoundException")
    void testGetByTitle() {
        String title = "inception";
        when(movieRepository.findByTitleContainingIgnoreCase(title)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> movieService.getByTitle(title));
    }

    @Test
    @DisplayName("Should return a list of movies by rating or throw NotFoundException")
    void testGetByRating() {
        Integer rating = 5;
        when(movieRepository.findByRating(rating)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> movieService.getByRating(rating));
    }

    @Test
    @DisplayName("Should return a list of movies by review or throw NotFoundException")
    void testGetByReview() {
        String review = "Ótimo filme";
        when(movieRepository.findByReviewContainingIgnoreCase(review)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> movieService.getByReview(review));
    }

    @Test
    @DisplayName("Should save a movie")
    void testSave() {
        MoviePostRequestBodyDTO dto = mock(MoviePostRequestBodyDTO.class);
        Movie movie = mock(Movie.class);
        when(movieRepository.save(any())).thenReturn(movie);

        Movie result = movieService.save(dto);

        assertNotNull(result);
        assertEquals(movie, result);
    }

    @Test
    @DisplayName("Should replace a movie or throw NotFoundException")
    void testReplaceSuccess() {
        MoviePutRequestBodyDTO dto = mock(MoviePutRequestBodyDTO.class);
        when(dto.getId()).thenReturn(1L);
        when(dto.getTitle()).thenReturn("Inception");
        when(dto.getRating()).thenReturn(5);
        when(dto.getReview()).thenReturn("Ótimo filme");

        Movie movie = new Movie();
        movie.setId(1L);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        assertDoesNotThrow(() -> movieService.replace(dto));
    }

    @Test
    @DisplayName("Should throw NotFoundException when replacing a movie that does not exist")
    void testReplaceError() {
        MoviePutRequestBodyDTO dto = mock(MoviePutRequestBodyDTO.class);
        when(dto.getId()).thenReturn(1L);
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> movieService.replace(dto));
    }

    @Test
    @DisplayName("Should delete a movie by ID or throw NotFoundException")
    void testDeleteSuccess() {
        Long id = 1L;
        Movie movie = mock(Movie.class);
        when(movieRepository.findById(id)).thenReturn(Optional.of(movie));

        assertDoesNotThrow(() -> movieService.delete(id));
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting a movie that does not exist")
    void testDeleteError() {
        Long id = 1L;
        when(movieRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> movieService.delete(id));
    }
}