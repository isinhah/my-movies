package com.api.movies.dtos.movies;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MoviePutRequestBodyDTO {
    @NotNull(message = "The movie ID cannot be null")
    private Long id;
    @NotBlank(message = "The title cannot be empty")
    private String title;
    @NotNull(message = "Rate the movie from 1 to 5")
    @Min(value = 1, message = "The rating must be at least 1")
    @Max(value = 5, message = "The rating must be at most 5")
    private Integer rating;
    private String review;
}