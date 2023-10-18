package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class SaveObjectTestUsingUtilClass {
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			if(tx == null)
				return;
			
			Product prod = new Product();
			
			prod.setPid(12);
			prod.setPname("Sony Home Theatre");
			prod.setPrice(98500.00f);
			prod.setQty(1.00f);
			
			
			ses.save(prod);
			
			tx.commit();
			
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
