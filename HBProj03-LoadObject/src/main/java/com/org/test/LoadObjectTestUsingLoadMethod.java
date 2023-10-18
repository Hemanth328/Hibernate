package com.org.test;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.org.entity.IProduct;
import com.org.entity.Product;

public class LoadObjectTestUsingLoadMethod {
	
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
			Product prod = ses.load(Product.class, 2);
//			IProduct prod = ses.load(Product.class, 2); // Proxy Interface
			/*	
				System.out.println(prod.getPid());
				
				System.out.println("----------------------");
				
				System.out.println(prod.getPname());
				
				System.out.println("----------------------");*/
				
				System.out.println(prod.getQty());
				
				System.out.println(prod.getClass()+"  "+prod.getClass().getSuperclass()+"  "+Arrays.toString(prod.getClass().getInterfaces()));
				
				System.out.println("----------------------");
				
				System.out.println(prod.getPid()+"  "+prod.getPname()+"  "+prod.getPrice()+"  "+prod.getQty());
				

		} catch (HibernateException he) {
			System.out.println("Record does not exist");
			he.printStackTrace();
		}

	}
}
