package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class DyamicSchemaCreationTestWithCreateDrop {
	public static void main(String[] args) {
		
		Configuration  cfg = new Configuration();
		
		cfg.configure("com/org/cfgs/hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session ses = factory.openSession();
		
		Transaction tx = null;
		
		try {
			
			Thread.sleep(30000);
			
			tx = ses.beginTransaction();
			
			Thread.sleep(30000);
			
			Product prod = new Product();
			
			prod.setPid(8879);
			prod.setPname("Television4");
			prod.setRating(3.8f);
			prod.setPrice(456876.00f);
//			prod.setStatus("Not-Delivered");
			
			Thread.sleep(30000);
			
			Integer idVal = (Integer)ses.save(prod);
			System.out.println("Generated Id Value is "+idVal);
			
			tx.commit();
			
			System.out.println("Product Saved Successfully");
			Thread.sleep(30000);
		}
		catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		ses.close();
		factory.close();
		
		System.out.println("Session Factory Object is closed");
	}
}
