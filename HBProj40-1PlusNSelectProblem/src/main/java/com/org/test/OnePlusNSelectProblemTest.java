package com.org.test;

import com.org.dao.CompanyDAOImpl;
import com.org.dao.ICompanyDAO;
import com.org.utility.HibernateUtil;

public class OnePlusNSelectProblemTest {

	public static void main(String[] args) {
		
		ICompanyDAO com = new CompanyDAOImpl();
				
		// Loading/Getting/Fetching the details of both records using HQL Join
		com.loadDataByParentUsingHQLJoin();
		
		// Loading/Getting/Fetching the details of both records using HQL Join
		com.loadDataByChildUsingHQLJoin();
		
		// Loading/Getting/Fetching the details of both records using JPA QBC 
		com.loadDataUsingJPAQBC();
				
		// closing Session Factory Object
		HibernateUtil.close();
	}
}
