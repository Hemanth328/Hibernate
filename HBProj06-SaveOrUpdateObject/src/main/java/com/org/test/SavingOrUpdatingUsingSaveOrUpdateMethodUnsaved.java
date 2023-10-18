package com.org.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.org.entity.Product;

public class SavingOrUpdatingUsingSaveOrUpdateMethodUnsaved {

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
			
			prod.setPid(7);
			prod.setPname("Sony Smart TV");
			prod.setPrice(160000.00f);
			prod.setQty(3.00f);
			
			
			ses.saveOrUpdate(prod);
			
			// Commit the Trx
			tx.commit();
			System.out.println("Record Inserted or Updated");

		} catch (HibernateException he) {
			
			if(tx!=null && tx.getStatus()!= null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Record failed to insert");
			}
			
			he.printStackTrace();
		}

	}
}
