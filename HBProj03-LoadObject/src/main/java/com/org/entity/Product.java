package com.org.entity;

import lombok.Data;

@Data
//public final class Product implements IProduct { // Proxy interface
public class Product {
  private Integer pid;
  private String pname;
  private  Float price;
  private  Float qty;
  
	public Product() {
	  System.out.println("Product:: 0-param constructor"+this.getClass());
	}
  
}