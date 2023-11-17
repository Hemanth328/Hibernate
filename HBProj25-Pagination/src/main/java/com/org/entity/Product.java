package com.org.entity;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "PRODUCT")
@Entity
@NamedQuery(name = "HQL_GET_PRODUCTS_BY_PRICE_RANGE", query = "from Product where price>= :min and price <= :max")
@NamedQuery(name= "INCREASE_PRODUCT_PRICE", query = "update Product set price = price+:newValue where price <= :range")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;

}