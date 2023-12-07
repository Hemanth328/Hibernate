package com.org.test;

import com.org.dao.IPersonDetailsDAO;
import com.org.dao.PersonDetailsDAOImpl;
import com.org.utility.HibernateUtil;

public class OneToManyUDTest {

	public static void main(String[] args) {
		
		IPersonDetailsDAO pd = new PersonDetailsDAOImpl();
		
		// Saving the details
		pd.saveDataUsingParent();
		
		// Loading/Getting/Fetching the details
		pd.loadDataUsingParent();
		
		// Adding Child to Existing Parent
		pd.addChildToExistingParent();
		
		// Deleting all the child records
		pd.deleteAllChildsOfParent();
		
		// Deleting the specific records of child table
		pd.deleteOneChildFromCollectionOfParent();
		
		// Deleting both the parent and child record at once
		pd.deleteParentAndChild();
		
		
		// closing Session Factory Object
		HibernateUtil.close();
	}
}
