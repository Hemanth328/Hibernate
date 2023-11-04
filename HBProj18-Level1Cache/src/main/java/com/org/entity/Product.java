package com.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "PRODUCT")
@Entity
public class Product {

	@Id
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;

}