package com.org.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;


@Data
@Table(name = "MOVIE_INFO_GEN")
@Entity
public  class Movie {
	
	@Id
//	@GenericGenerator(name = "gen1", strategy = "increment")
//	@GenericGenerator(name = "gen1", strategy = "identity")
	@GenericGenerator(name = "gen1", strategy = "sequence")
	@GeneratedValue(generator = "gen1")
	private Integer mid;

	private String mname;

	private Float rating;
	
	private String hero;
	
//	@Transient
	private Long budget;

}