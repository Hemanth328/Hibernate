package com.org.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.org.entity.Product;

public class LoadObjectTestUsingGetMethod {

	public static void main(String[] args) {

		// Configutration obj
		Configuration cfg = new Configuration();
		cfg.configure("com/org/cfgs/hibernate.cfg.xml");

		// build SEssionFactory obj
		SessionFactory factory = cfg.buildSessionFactory();

		// build Session
		Session ses = factory.openSession();

		try (factory; ses) { // try with resource

			// Load object using get(-,-) method
			Product prod = ses.get(Product.class, 1);

			System.out.println(prod.getClass());

			if (prod == null)
				System.out.println("Product not found");
			else
				System.out.println(prod);

		} catch (HibernateException he) {
			he.printStackTrace();
		}

	}
}
