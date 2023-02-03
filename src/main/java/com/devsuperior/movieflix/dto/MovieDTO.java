package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

	private Long id;
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;
	private String synopsis;
	private GenreDTO genre;

	
	public MovieDTO(Movie movie) {
		id = movie.getId();
		title = movie.getTitle();
		subTitle= movie.getSubTitle();
		year = movie.getYear();
		imgUrl = movie.getImgUrl();
		synopsis = movie.getSynopsis();
		genre= new GenreDTO(movie.getGenre());

	}

	public void setMovieId(long l) {
		// TODO Auto-generated method stub
		
	}
}