package com.org.test;

import com.org.dao.CompanyDAOImpl;
import com.org.dao.ICompanyDAO;
import com.org.utility.HibernateUtil;

public class OneToManyOrManyToOneBDTest {

	public static void main(String[] args) {
		
		ICompanyDAO com = new CompanyDAOImpl();
				
		// Loading/Getting/Fetching the details using parent
		com.loadDataUsingParent();
		
		// Loading/Getting/Fetching the details using parent
		com.loadDataUsingChild();
				
		// closing Session Factory Object
		HibernateUtil.close();
	}
}
