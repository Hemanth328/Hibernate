package com.org.test;

import com.org.dao.HospitalDAOImpl;
import com.org.dao.IHospitalDAO;
import com.org.utility.HibernateUtil;

public class ManyToManyBDTest {

	public static void main(String[] args) {
		
		// Craete DAO class object
		IHospitalDAO hos = new HospitalDAOImpl();
		
		// Saving datga using parent
//		hos.saveDataUsingParent();
		
		// Saving data using child
//		hos.saveDataUsingChild();
		
		// Loading data using parent
		hos.loadDataUsingParent();
		
		// closing Session Factory Object
		HibernateUtil.close();
	}
}
