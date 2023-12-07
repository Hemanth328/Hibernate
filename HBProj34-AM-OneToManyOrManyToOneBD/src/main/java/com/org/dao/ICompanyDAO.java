package com.org.dao;

public interface ICompanyDAO {
	
	public void saveDataUsingParent();
	
	public void saveDataUsingChild();
	
	public void loadDataUsingParent();
	
	public void loadDataUsingChild();
	
	public void addChildToExistingParent();
	
	public void deleteAllChildsOfParent();
	
	public void deleteOneChildFromCollectionOfParent();
	
	public void deleteParentAndChild();
}
