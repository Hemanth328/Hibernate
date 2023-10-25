package com.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Table(name = "PRODUCT_INFO")
@Entity
public class Product {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Pid;

	private String pname;
	
	private Float rating;
	
	private Float price;
	
//	private String status;

}