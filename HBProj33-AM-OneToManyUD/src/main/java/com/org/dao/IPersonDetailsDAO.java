package com.org.dao;

public interface IPersonDetailsDAO {
	
	public void saveDataUsingParent();
	
	public void loadDataUsingParent();
	
	public void addChildToExistingParent();
	
	public void deleteAllChildsOfParent();
	
	public void deleteOneChildFromCollectionOfParent();
	
	public void deleteParentAndChild();
}
