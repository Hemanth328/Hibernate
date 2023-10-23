package com.org.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;


@Data
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "MOVIE_INFO")
@Entity
public  class Movie {
	
	@Id
	@Column(name = "MID")
	private Integer mid;

	@Column(name = "M_NAME")
	private String mname;
	
	@Column(name = "M_RATING")
	private Float rating;
	
	@Column(name = "M_HERO")
	private String hero;
	
//	@Transient
	@Column(name = "M_BUDGET")
	private Long budget;

}