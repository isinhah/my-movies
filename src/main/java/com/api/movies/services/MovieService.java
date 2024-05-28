package com.api.movies.services;

import com.api.movies.domain.Movie;
import com.api.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }
}