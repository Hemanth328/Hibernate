package com.org.test;

import com.org.dao.ILibraryDAO;
import com.org.dao.LibraryDAOImpl;
import com.org.utility.HibernateUtil;

public class OneToOneBDPKTest {

	public static void main(String[] args) {
		
		// Create DAO class object
		ILibraryDAO lib = new LibraryDAOImpl();
		
		// Saving data using parent
//		lib.saveDataUsingParent();
		
		// Saving data using child
//		lib.saveDataUsingChild();
		
		// Load data using Parent
//		lib.loadDataUsingParent();
		
		// Load data using Child
		lib.loadDataUsingChild();
		
		// closing Session Factory Object
		HibernateUtil.close();
	}
}
