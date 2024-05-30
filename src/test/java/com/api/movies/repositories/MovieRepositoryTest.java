package com.api.movies.repositories;

import com.api.movies.domain.Movie;
import com.api.movies.dtos.movies.MoviePostRequestBodyDTO;
import com.api.movies.mappers.MovieMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
@DisplayName("Tests for Movie Repository")
@ActiveProfiles("test")
class MovieRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MovieRepository movieRepository;

    @Test
    @DisplayName("Should find movies by title containing string ignoring case")
    void findByTitleContainingIgnoreCaseSuccess() {
        MoviePostRequestBodyDTO data1 = new MoviePostRequestBodyDTO("Inception", 5, "Amei o filme");
        MoviePostRequestBodyDTO data2 = new MoviePostRequestBodyDTO("Interstellar", 4, "Ótimo filme");
        this.createMovie(data1);
        this.createMovie(data2);

        List<Movie> result = this.movieRepository.findByTitleContainingIgnoreCase("incep");

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Inception");
    }

    @Test
    @DisplayName("Should not find movies when title does not contain the given string")
    void findByTitleContainingIgnoreCaseError() {
        MoviePostRequestBodyDTO data1 = new MoviePostRequestBodyDTO("Inception", 5, "Amei o filme");
        MoviePostRequestBodyDTO data2 = new MoviePostRequestBodyDTO("Interstellar", 4, "Ótimo filme");
        this.createMovie(data1);
        this.createMovie(data2);

        List<Movie> result = this.movieRepository.findByTitleContainingIgnoreCase("Spiderman");

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should get Movie successfully from database")
    void findByRatingSuccess() {
        MoviePostRequestBodyDTO data = new MoviePostRequestBodyDTO("Inception", 5, "Amei o filme");
        this.createMovie(data);

        List<Movie> result = this.movieRepository.findByRating(5);

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Inception");
    }

    @Test
    @DisplayName("Should not get Movie from database when movie not exists")
    void findByRatingError() {
        List<Movie> result = this.movieRepository.findByRating(5);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should find movies by review containing string ignoring case")
    void findByReviewContainingIgnoreCase() {
        MoviePostRequestBodyDTO data1 = new MoviePostRequestBodyDTO("Inception", 5, "Amei o filme");
        MoviePostRequestBodyDTO data2 = new MoviePostRequestBodyDTO("Interstellar", 4, "Ótimo filme");
        this.createMovie(data1);
        this.createMovie(data2);

        List<Movie> result = this.movieRepository.findByReviewContainingIgnoreCase("filme");

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getReview()).isEqualTo("Amei o filme");
        assertThat(result.get(1).getReview()).isEqualTo("Ótimo filme");
    }

    @Test
    @DisplayName("Should not find movies when review does not contain the given string")
    void findByReviewContainingIgnoreCaseError() {
        MoviePostRequestBodyDTO data1 = new MoviePostRequestBodyDTO("Inception", 5, "Amei o filme");
        MoviePostRequestBodyDTO data2 = new MoviePostRequestBodyDTO("Interstellar", 4, "Ótimo filme");
        this.createMovie(data1);
        this.createMovie(data2);

        List<Movie> result = this.movieRepository.findByReviewContainingIgnoreCase("Não gostei dos efeitos especiais");

        assertThat(result).isEmpty();
    }

    private void createMovie(MoviePostRequestBodyDTO data) {
        Movie newMovie = MovieMapper.toEntityMovie(data);
        this.entityManager.persist(newMovie);
    }
}