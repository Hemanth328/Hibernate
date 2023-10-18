package com.org.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.org.entity.Product;

public class LoadAndDeleteObjectTestUsingDeleteMethod {

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
			
			// Prepare object of Entity class
			Product prod = ses.get(Product.class, 4);
			
			if(prod == null) {
				System.out.println("Record does not exist");
				return;
			}
			else {
				//Begin Trx
				tx = ses.beginTransaction();
				
				ses.delete(prod);
				
				// Commit the Trx
				tx.commit();
				System.out.println("Object is deleted");
			}

		} catch (HibernateException he) {
			
			if(tx!=null && tx.getStatus()!= null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object not found");
			}
			
			he.printStackTrace();
		}

	}
}
