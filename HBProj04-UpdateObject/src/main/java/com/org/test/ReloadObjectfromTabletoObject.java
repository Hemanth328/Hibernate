package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.org.entity.Product;

public class ReloadObjectfromTabletoObject {
	

	public static void main(String[] args) throws InterruptedException {

		// Configutration obj
		Configuration cfg = new Configuration();
		cfg.configure("com/org/cfgs/hibernate.cfg.xml");

		// build SEssionFactory obj
		SessionFactory factory = cfg.buildSessionFactory();

		// build Session
		Session ses = factory.openSession();

		try (factory; ses) { // try with resource

			// Prepare object of Entity class
			
			Product prod = ses.get(Product.class, 1);
			
			if(prod == null) {
				System.out.println("Record Not Found");
				return;
			}
			else {
				
				// display record
				System.out.println(prod);
				
				System.out.println("App is about to sleep");
				Thread.sleep(60000);
				
				// during this update the record
				
				ses.refresh(prod); // reloads the object table data from the record and generates a new sql query
				
				System.out.println(prod);
			}
			

		} catch (Exception he) {
			
			he.printStackTrace();
		}

	}

}
