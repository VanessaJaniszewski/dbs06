package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Page<Review> findByUser(User user, Pageable pageable);

	User findByUser(User user);
	
//	@Query(nativeQuery = true, value = "select * from tb_review where tb_review.movie_id in "
//			+ " (select tb_movie.id from tb_movie where tb_movie.genre_id = :genreId "
//			+ "order by tb_movie.title )")
//	Page<Review> findByGenreId(Long genreId, Pageable pageable);
//
//	 @Query(nativeQuery = true, value ="SELECT * FROM tb_review " +
//	            "where tb_review.movie.id = :movieId")
//	    List<Review> findAllReviewsByMovieId(@Param("movieId")Long idMovie);
	 
	 
	 @Query("SELECT r FROM Review r " +
	            "WHERE r.movie.id = :idMovie")
	    List<Review> findAllReviewsByMovieId(@Param("idMovie")Long idMovie);

}
