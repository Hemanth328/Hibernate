package com.org.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.org.entity.Product;

public class UpdateObjectTestUsingUpdateMethod {

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
			
			Product prod = new Product();
			prod.setPid(2); // Must be an existingId
			prod.setPname("IFB Washing Machine");
			prod.setPrice(28999.00f);
			prod.setQty(1.00f);
			
			ses.update(prod);
			
			// Commit the Trx
			tx.commit();
			System.out.println("Object is closed");

		} catch (HibernateException he) {
			
			if(tx!=null && tx.getStatus()!= null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object has not been updated");
			}
			
			he.printStackTrace();
		}

	}
}
