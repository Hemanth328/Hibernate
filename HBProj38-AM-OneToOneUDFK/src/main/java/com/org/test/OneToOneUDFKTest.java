package com.org.test;

import com.org.dao.IPassPortDAO;
import com.org.dao.PassportDAOImpl;
import com.org.utility.HibernateUtil;

public class OneToOneUDFKTest {

	public static void main(String[] args) {
		
		// Create DAO class object
		IPassPortDAO pp = new PassportDAOImpl();
		
		// Saving data using child
		pp.saveDataUsingChild();
		
		// Load data using child
		pp.loadDataUsingChild();
		
		// Load data using child
		pp.loadDataUsingParent();
		
		// closing Session Factory Object
		HibernateUtil.close();
	}
}
