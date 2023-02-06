package com.devsuperior.movieflix.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.ReviewInsertDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.service.exceptions.ForbiddenException;
import com.devsuperior.movieflix.service.exceptions.UnauthorizedException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	@Autowired
	private MovieRepository mrepository;
	@Autowired
	private UserRepository urepository;
	@Autowired
	private AuthService authService;

	 @PreAuthorize("hasAnyRole('MEMBER')")
	    @Transactional
	    public ReviewDTO saveReview(ReviewDTO reviewDTO){

	    try{
	        Review newReview = new Review();
	        User user = authService.authenticated();
	        newReview.setId(reviewDTO.getId());
	        newReview.setText(reviewDTO.getText());
	        newReview.setMovie(mrepository.getOne(reviewDTO.getMovieId()));
	        newReview.setUser(user);
	        repository.save(newReview);
	        return new ReviewDTO(newReview);
	    } catch (RuntimeException e){
	        throw new UnauthorizedException("Usuário não autorizado");
	    }}

//	@Transactional(readOnly = true)
//	public List<ReviewDTO> movieReviews(Long movieId) {
//		User user = authService.authenticated();
//		try {
//			Movie movie = mrepository.getOne(movieId);
//			List<Review> reviews = repository.findByMovie(movie);
//			return reviews.stream().map(x-> new ReviewDTO(x)).collect(Collectors.toList());
//		}
//		catch(EntityNotFoundException e) {
//			throw new ResourceNotFoundException("Id wasn't found: "+movieId);
//		}}

	
	@Transactional
	public void insert(@Valid ReviewInsertDTO dto) {
		User user = authService.authenticated();
		if(user == null ) {
			throw new ForbiddenException("You need to login to make a review");
		}else {
		try {
			
			Review review = new Review();
			review.setText(dto.getText());
			review.setMovie(mrepository.getOne(dto.getMovieId()));
			review.setUser(urepository.getOne(user.getId()));
			repository.save(review);
		}
		catch(ForbiddenException e) {
			throw new ForbiddenException("You need to login to make a review");
		}
		}}



	}

	
	


