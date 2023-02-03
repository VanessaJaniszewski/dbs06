package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.service.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	private static Logger logger = LoggerFactory.getLogger(GenreService.class);
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private MovieRepository repository;
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	@Transactional(readOnly = true)
	public UserDTO getProfile() {
		User user = authService.authenticated();
		return new UserDTO(user);
	}


	@Transactional
	public MovieDTO findOneMovieById(Long id) {
		User user = authService.authenticated();
		try {
			Movie obj = repository.findOneMovieById(id);
			if(obj!=null) {
			MovieDTO dto = new MovieDTO(obj);
			return dto;}
		else {
			throw new ResourceNotFoundException("No id found.");
		}}
		catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException("No id found.");
			}
	}

	@Transactional
	public List<MovieDTO> findAllMovies() {
		User user = authService.authenticated();
		try {
			List<Movie> movies = repository.findAll();
			List<MovieDTO> dtos = movies.stream().map(x-> new MovieDTO(x)).collect(Collectors.toList());
			return dtos;}
		catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException("No id found.");
			}
	
	}

	@Transactional
	public List<ReviewDTO> findAllReviewsByMovieId(Long movieId) {
		List<Review> reviews = reviewRepository.findAllReviewsByMovieId(movieId);
		List<ReviewDTO> dtos = reviews.stream().map(x-> new ReviewDTO(x)).collect(Collectors.toList());
		return dtos;
	}
	

	@Transactional
	public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
		User user = authService.authenticated();
		try {
		Page<Movie> movies = repository.findByGenre(genreId, pageable);
		Page<MovieDTO> dtos = movies.map(x-> new MovieDTO(x));	
		return dtos;}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("No id found.");
		}
	}
	
}

	

