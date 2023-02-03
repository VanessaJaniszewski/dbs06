package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(nativeQuery = true, value = "Select * from tb_movie where tb_movie.id= :id")
	Movie findOneMovieById(Long id);

	@Query(nativeQuery = true, value = "Select ")
	List<Movie> findByMovieId(@Param("movieId")Long movieId);

	@Query(nativeQuery = true, value = "Select * from tb_movie "
			+ "where tb_movie.genre_id = :genreId "
			+ "order by tb_movie.title")
	Page<Movie> findByGenre(@Param("genreId") Long genreId, Pageable pageable);
	


}
