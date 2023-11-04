package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class SaveObjectTestCaching {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (factory; ses) {

			Product prod1 = ses.get(Product.class, 9); // Collects from DB table and stores in L1 cache

			System.out.println(prod1);

			System.out.println("==============================");

			Product prod2 = ses.get(Product.class, 9); // Collects from DB table and stores in L1 cache

			System.out.println(prod2);
			
			System.out.println("==============================");

			Product prod3 = ses.get(Product.class, 10); // Collects from DB table and stores in L1 cache

			System.out.println(prod3);

			ses.evict(prod1); // Removes Product obj with id value 9 from L1 cahce
			System.out.println("==============================");

			Product prod4 = ses.get(Product.class, 9); // Collects from DB table and stores in L1 cache

			System.out.println(prod4);
			
			ses.clear(); // Removes all the objects from L1 cache
			System.out.println("==============================");

			Product prod5 = ses.get(Product.class, 9); // Collects from DB table and stores in L1 cache

			System.out.println(prod5);
		}
		
		catch (Exception e) {
			
		}
	}
}
