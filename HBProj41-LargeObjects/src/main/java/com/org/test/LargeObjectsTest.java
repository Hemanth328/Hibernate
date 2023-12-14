package com.org.test;

import com.org.dao.CompanyDAOImpl;
import com.org.dao.ICompanyDAO;
import com.org.utility.HibernateUtil;

public class LargeObjectsTest {

	public static void main(String[] args) {
		
		ICompanyDAO com = new CompanyDAOImpl();
				
//		com.saveData();
		com.fetchData();
				
		// closing Session Factory Object
		HibernateUtil.close();
	}
}
