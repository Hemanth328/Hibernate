package com.org.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Transient;
import lombok.Data;


@Data
@Table(name = "MOVIE_INFO_JPAGEN")
@Entity
public  class Movie {
	
	/*@Id
	@SequenceGenerator(name = "gen1", initialValue = 200, sequenceName = "JPA_MID_SEQ", allocationSize = 5)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)*/
	
	/*@Id
	@TableGenerator(name = "gen1", pkColumnName = "Id_col", table = "id_tab",
	                pkColumnValue = "val_col", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.TABLE)*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;

	private String mname;

	private Float rating;
	
	private String hero;
	
//	@Transient
	private Long budget;

}