package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
public class MovieController {

	@Autowired
	private MovieService service;
	
	
	@GetMapping(value ="/movies/{movieId}")
	public ResponseEntity<MovieDTO> findOneMovieById(
			@PathVariable Long movieId) {
		MovieDTO dto = service.findOneMovieById(movieId);
		return ResponseEntity.ok().body(dto);	}
	
	@GetMapping(value="/movies/0")
	public ResponseEntity<List<MovieDTO>> findAllMovies(){
		List<MovieDTO> dtos = service.findAllMovies();
		return ResponseEntity.ok().body(dtos);
	}
	
	@GetMapping(value="/movies/{genreId}/genres")
	public ResponseEntity<Page<MovieDTO>> findByGenre(
			@PathVariable Long genreId, Pageable pageable){
		Page<MovieDTO> dto = service.findByGenre(genreId, pageable);
		return ResponseEntity.ok().body(dto);	}
	
	

	@GetMapping(value = "movies/{movieId}/reviews")
	public ResponseEntity<List<ReviewDTO>> findAllReviewsByMovieId(
			@PathVariable Long movieId){
    List<ReviewDTO> reviewDTO = service.findAllReviewsByMovieId(movieId);
    return ResponseEntity.ok().body(reviewDTO);
}
}


	
