package com.org.test;

import com.org.dao.CompanyDAOImpl;
import com.org.dao.ICompanyDAO;
import com.org.utility.HibernateUtil;

public class OneToManyOrManyToOneBDTest {

	public static void main(String[] args) {
		
		ICompanyDAO com = new CompanyDAOImpl();
		
		// Saving the details using parent
//		com.saveDataUsingParent();
		
		// Saving the details using child
//		com.saveDataUsingChild();
		
		// Loading/Getting/Fetching the details using parent
//		com.loadDataUsingParent();
		
		// Loading/Getting/Fetching the details using parent
		com.loadDataUsingChild();
		
		// Deleting all the child records
//		com.deleteAllChildsOfParent();
		
		// Deleting the specific records of child table
//		com.deleteOneChildFromCollectionOfParent();
		
		// Deleting both the parent and child record at once
//		com.deleteParentAndChild();
		
		// closing Session Factory Object
		HibernateUtil.close();
	}
}
