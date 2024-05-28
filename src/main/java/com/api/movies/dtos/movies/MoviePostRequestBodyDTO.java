package com.api.movies.dtos.movies;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePostRequestBodyDTO {
    @NotBlank(message = "The title cannot be empty")
    private String title;
    @NotNull(message = "Rate the movie from 1 to 5") @Min(1) @Max(5)
    private Integer rating;
    private String review;
}