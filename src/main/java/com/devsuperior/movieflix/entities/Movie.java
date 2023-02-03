package com.devsuperior.movieflix.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.sql.LongNVarcharTypeDescriptor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_movie")
public class Movie implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;
	
	@Type(type = "text")
	private String synopsis;
	
	@OneToMany(mappedBy ="movie")
	private Set<Review> reviews = new HashSet<>();
	
	@ManyToOne
	@JoinColumn (name = "genre_id")
	private Genre genre;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
