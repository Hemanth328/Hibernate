package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class MergeObjectTestUsingUtilClass {
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			Product prod = new Product();
			
			prod.setPid(5);
			prod.setPname("tables");
			prod.setPrice(52000.00f);
			prod.setQty(1.00f);
			
			
			Product pro = (Product)ses.merge(prod);
			
			tx.commit();
			System.out.println("Given Objecdata "+prod+" HashCode "+prod.hashCode());
			System.out.println("Received Objecdata "+pro+" HashCode "+pro.hashCode());
			System.out.println("Record Inserted Successfully");
		}
		catch(Exception e) {
			
			if(tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Record failed to insert");
			}
			
			e.printStackTrace();
		}
	}

}
