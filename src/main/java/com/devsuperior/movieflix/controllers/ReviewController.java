package com.devsuperior.movieflix.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.ReviewInsertDTO;
import com.devsuperior.movieflix.service.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	private ReviewService service;
	
//	@PreAuthorize("hasAnyRole ('MEMBER')")
//	@PutMapping (value = "/reviews/{id}")
//	public ResponseEntity<Void> saveReview(
//			@PathVariable @RequestParam(defaultValue = "0") Long id,
//			@RequestBody ReviewDTO dto) {
//		service.saveReview(id, dto);
//		return ResponseEntity.noContent().build();
//	}
	
	@PreAuthorize("hasAnyRole ('MEMBER')")
	@PostMapping(value = "/reviews")
	public ResponseEntity<Void> insert(@Valid @RequestBody ReviewInsertDTO dto) {
		try {
		service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}catch (ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}	
}
	
	

}
