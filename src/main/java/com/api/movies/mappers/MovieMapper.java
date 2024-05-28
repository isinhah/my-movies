package com.api.movies.mappers;

import com.api.movies.domain.Movie;
import com.api.movies.dtos.movies.MoviePostRequestBodyDTO;
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
}