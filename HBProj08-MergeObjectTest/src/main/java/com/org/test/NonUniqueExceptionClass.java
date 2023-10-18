package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class NonUniqueExceptionClass {
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			Product prod = ses.get(Product.class, 8);
			
			Product pro = new Product();
			pro.setPid(8);
			pro.setPname("Router");
			pro.setPrice(5600.00f);
			pro.setQty(1.00f);
			
			ses.update(pro); // Object will be saved in L1 cache of Session object but already an object exists with the same id due 
			//to which it raises the NonUniqueObjectException( A different object with the same identifier value was already associated with the session)
			
			tx.commit();
			
			System.out.println("Object saved");
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}

}
