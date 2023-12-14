package com.org.test;

import com.org.dao.CompanyDAOImpl;
import com.org.dao.ICompanyDAO;
import com.org.utility.HibernateUtil;

public class OneToManyOrManyToOneBDTest {

	public static void main(String[] args) {
		
		ICompanyDAO com = new CompanyDAOImpl();
		
		// proxy loading
		com.loadDataUsingChildWithProxy();
		
		// closing Session Factory Object
		HibernateUtil.close();
	}
}
