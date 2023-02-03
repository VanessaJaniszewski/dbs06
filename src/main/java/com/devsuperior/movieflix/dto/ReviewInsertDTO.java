package com.devsuperior.movieflix.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.Type;

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
public class ReviewInsertDTO {

	
	@NotBlank(message = "Please insert a comment.")
	private String text;
	@Type ( type = "Long")
	private Long movieId;
	
	
	public ReviewInsertDTO(Review review) {
		text = review.getText();
		movieId= review.getMovie().getId();
		
	}
}
