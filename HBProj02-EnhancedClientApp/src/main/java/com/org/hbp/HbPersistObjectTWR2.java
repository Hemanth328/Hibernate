package com.org.hbp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.org.entity.Product;


//Try with resource 9 version

public class HbPersistObjectTWR2 {
	
	 public static void main( String[] args ) {
	 	
	   // Bootstrap/activate the Hibernate
	 	Configuration cfg = new Configuration();
	 	
	 	//specifying the Hibernate Configuration file name and location
	 	cfg.configure("com/org/cfgs/hibernate.cfg.xml");
	
	 	
	 	SessionFactory factory = cfg.buildSessionFactory();
		
		Session ses = factory.openSession();
	 	
	 	
	 	Transaction tx = null;
	 	boolean flag = false;
	 	
	 	
	 	try(factory; ses) {
	 		// begin transaction
	 		tx = ses.beginTransaction(); // internally calls con.setAutoCommit(false) to disable auto commit mode on DB s/w 
	 		// Prepare entity object
	 		
	 		Product prod = new Product();
	 		
	 		prod.setPid(1001);
//	 		prod.setPname("Chairs");
//	 		prod.setPrice(11456.33f);
	 		prod.setQty(6.0f);
	 		
	 		// save object
	 		Integer idval = (Integer)ses.save(prod); // gives persistence instruction to save the object (insert object data as the record) this returns the id value
	
	 		ses.persist(prod); // gives persistence instruction to save the object (insert object data as the record) this returns the id value
	 		
	 		tx.commit(); // internally calls con.commit method to make insertion execution results permanent
	// 		
	// 		System.out.println("Generator retuned Id value is : "+idval);
	 		
	 		System.out.println("Object is saved (Record is inserted)");
	 		
	 		
	 		flag = true;
	 		
	 	}
	 	
	 	catch(HibernateException he) {
	 		he.printStackTrace(); 		
	 		if(tx!= null && tx.getRollbackOnly() && tx.getStatus() != null) {
	 			tx.rollback();
	 			System.out.println("Object is not saved");
	 		}
	 	}
	 	
	     
	 }
}