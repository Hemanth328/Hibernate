package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			for(int i = 0; i<5; i++) {
				
				tx = ses.beginTransaction();
				
				Product prod = new Product();
				
//				prod.setPid(151);
				prod.setPname("Table"+i);
				prod.setPrice(15000.0f);
				prod.setQty(1.0f);
				
				ses.save(prod);
				
				System.out.println("Object Saved Successfully");
				
				tx.commit();
			}
						
		}
		catch(Exception e) {
			
			if(tx!= null && tx.getStatus()!= null && tx.getRollbackOnly()) {
				System.out.println("Object Failed to Insert");
				tx.rollback();
			}
			
			e.printStackTrace();
		}
	}

}
