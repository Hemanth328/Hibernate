package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class DyamicSchemaCreationTest {
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			Product prod = new Product();
			
			prod.setPid(8978);
			prod.setPname("Television4");
			prod.setRating(3.8f);
			prod.setPrice(456876.00f);
//			prod.setStatus("Not-Delivered");
			
			ses.save(prod);
			
			tx.commit();
			
			System.out.println("Product Saved Successfully");
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
}
