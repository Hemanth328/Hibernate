package com.org.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil_Oracle {
	
	private static SessionFactory factory;
	
	static {
		
		System.out.println("HibernateUtil_Oracle.static block");
		
		//create configuration object
		Configuration cfg = new Configuration();
		
		// Load and read HB Configuration file
		cfg.configure("com/org/cfgs/hibernate-oracle.cfg.xml");
		
		//build SessionFactory
		
		factory = cfg.buildSessionFactory();
	}
	
	// static factory method for SessionFactory object
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	// static factory method for session obj
	public static Session getSession() {
		
		Session ses = null;
		
		if(factory != null)
			ses = factory.openSession();
		
		return ses;
	}
}
