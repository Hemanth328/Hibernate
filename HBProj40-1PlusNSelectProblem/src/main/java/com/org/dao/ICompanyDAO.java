package com.org.dao;

public interface ICompanyDAO {
	
	public void loadDataByParentUsingHQLJoin();
	
	public void loadDataByChildUsingHQLJoin();
	
	public void loadDataUsingJPAQBC();
}
