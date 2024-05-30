package com.api.movies.mappers;

import com.api.movies.domain.Movie;
import com.api.movies.dtos.movies.MoviePostRequestBodyDTO;
import com.api.movies.dtos.movies.MoviePutRequestBodyDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class MovieMapper {
    public static Movie toEntityMovie(MoviePostRequestBodyDTO moviePostRequestBodyDTO) {
        Movie movie = new Movie();
        movie.setTitle(moviePostRequestBodyDTO.getTitle());
        movie.setRating(moviePostRequestBodyDTO.getRating());
        movie.setReview(moviePostRequestBodyDTO.getReview());
        movie.setLogDate(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        return movie;
    }

    public static Movie toEntityMovie(MoviePutRequestBodyDTO moviePutRequestBodyDTO) {
        Movie movie = new Movie();
        movie.setTitle(moviePutRequestBodyDTO.getTitle());
        movie.setRating(moviePutRequestBodyDTO.getRating());
        movie.setReview(moviePutRequestBodyDTO.getReview());
        movie.setLogDate(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        return movie;
    }

    public static MoviePostRequestBodyDTO toDTO(Movie movie) {
        MoviePostRequestBodyDTO dto = new MoviePostRequestBodyDTO();
        dto.setTitle(movie.getTitle());
        dto.setRating(movie.getRating());
        dto.setReview(movie.getReview());
        return dto;
    }
}