package com.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "PRODUCT")
@Entity
@NamedNativeQuery(name = "GET_PRODS_BY_PRICE_RANGE", query = "select * from product where price>= ? and price <= ?")
@NamedNativeQuery(name = "HIKE_PRICE_BY_PROD_NAME", query ="update product set price = price+:incAmount where pname= :name")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;

}