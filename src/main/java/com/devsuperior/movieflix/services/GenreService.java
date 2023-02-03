package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.GenreRepository;

@Service
public class GenreService{

	private static Logger logger = LoggerFactory.getLogger(GenreService.class);

	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private AuthService authService;
	
//	@Transactional(readOnly = true)
//	public UserDTO getProfile(Long id) {
//		authService.validateSelf(id);
//		Optional<User> obj = repository.findById(id);
//		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
//		return new UserDTO(entity);
//	}
	@Transactional(readOnly = true)
	public UserDTO getProfile() {
		User user = authService.authenticated();
		return new UserDTO(user);
	}
	
	@Transactional(readOnly = true)
	public List<GenreDTO> viewGenres() {
		User user = authService.authenticated();
		List<Genre> page = genreRepository.find();
		return page.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
	}
}
