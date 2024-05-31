package com.api.movies.controllers;

import com.api.movies.domain.Movie;
import com.api.movies.dtos.movies.MoviePostRequestBodyDTO;
import com.api.movies.dtos.movies.MoviePutRequestBodyDTO;
import com.api.movies.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies", produces = {"application/json"})
@Tag(name = "movies-api")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Operation(summary = "Retorna uma lista paginada de todos os filmes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista paginada retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Movie> listAll(Pageable pageable) {
        return movieService.listAll(pageable).getContent();
    }

    @Operation(summary = "Retorna todos os filmes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filmes retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @Operation(summary = "Retorna um filme pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getByIdOrThrowNotFoundException(id));
    }

    @Operation(summary = "Retorna filmes pelo título", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filmes retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/find/by-title")
    public ResponseEntity<List<Movie>> getByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.getByTitle(title));
    }

    @Operation(summary = "Retorna filmes pela classificação", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filmes retornados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/find/by-rating")
    public ResponseEntity<List<Movie>> getByRating(@RequestParam Integer rating) {
        return ResponseEntity.ok(movieService.getByRating(rating));
    }

    @Operation(summary = "Retorna filmes pela análise", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filmes retornados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/find/by-review")
    public ResponseEntity<List<Movie>> getByReview(@RequestParam String review) {
        return ResponseEntity.ok(movieService.getByReview(review));
    }

    @Operation(summary = "Cria um novo filme", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Filme criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody @Valid MoviePostRequestBodyDTO data) {
        return new ResponseEntity<>(movieService.save(data), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um filme existente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Filme atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid MoviePutRequestBodyDTO data) {
        movieService.replace(data);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Exclui um filme pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Filme excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}