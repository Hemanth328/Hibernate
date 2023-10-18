package com.org.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.org.entity.Product;

public class PartialUpdateTestWithOrWithoutUpdateMethod {

	public static void main(String[] args) {

		// Configutration obj
		Configuration cfg = new Configuration();
		cfg.configure("com/org/cfgs/hibernate.cfg.xml");

		// build SEssionFactory obj
		SessionFactory factory = cfg.buildSessionFactory();

		// build Session
		Session ses = factory.openSession();
		
		Transaction tx = null;

		try (factory; ses) { // try with resource
			
			//Begin Trx
			tx = ses.beginTransaction();

			// Prepare object of Entity class
			
			Product prod = ses.get(Product.class, 1);
			
			if(prod == null) {
				System.out.println("Record Not Found");
				return;
			}
			else {
//				prod.setPname("IFB Micro-Oven");
//				prod.setPrice(19500.00f);
				
				prod.setQty(2.00f);
				
//				ses.update(prod); // using update method
				
				// Commit the Trx
				tx.commit();
				System.out.println("Partial Update is Done");
			}
			
			
			

		} catch (HibernateException he) {
			
			if(tx!=null && tx.getStatus()!= null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object has not been updated");
			}
			
			he.printStackTrace();
		}

	}
}
