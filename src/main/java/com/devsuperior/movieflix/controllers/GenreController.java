package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.GenreService;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {

	@Autowired
	private GenreService service;
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> viewGenre() {
		
		List<GenreDTO> page = service.viewGenres();		
		return ResponseEntity.ok().body(page);
	}
	
	
}
