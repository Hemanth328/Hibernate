package com.org.entity;

import lombok.Data;

@Data
public class Product {
	
//  private Integer pid;
  private String pid; // for UUID & GUID Id should be String
  private String pname;
  private  Float price;
  private  Float qty;
  
}