package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class SaveObjectGUIDGenerator {
public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			for(int i = 0; i<3; i++) {
				
				tx = ses.beginTransaction();
				
				Product prod = new Product();
				
//				prod.setPid(151);
				prod.setPname("Chair"+i);
				prod.setPrice(15000.0f);
				prod.setQty(1.0f);
				
				Thread.sleep(1000); // during this sleep period the same application is being run multiple times
				
				String idval = (String)ses.save(prod);
				
				System.out.println("Object Saved Successfully with the id "+idval);
				
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
