package com.devsuperior.movieflix.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

	private Long id;
	@NotBlank(message = "Please insert a comment.")
	private String text;
	private User user;
	private Movie movie;
	private Long movieId;
	private Long userId;
	
	public ReviewDTO(Review review) {
		id = review.getId();
		text = review.getText();
		user= review.getUser();
		movie= review.getMovie();
		movieId= review.getMovie().getId();
		userId = review.getUser().getId();
		
	}
}
