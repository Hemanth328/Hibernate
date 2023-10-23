package com.org.entity;

public interface IMovie {

	public Integer getMid();
	
	public String getMname();
	
	public Float getRating();
	
	public String getHero();
	
	public Long getBudget();
	
	public void setMid(Integer mid);

	public void setMname(String mname);

	public void setRating(Float rating);

	public void setHero(String hero);	

	public void setBudget(Long budget);

}
