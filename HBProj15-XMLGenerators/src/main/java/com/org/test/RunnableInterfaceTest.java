package com.org.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class RunnableInterfaceTest implements Runnable{
	@Override
	public void run() {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx  = null;
		try(ses) {
			
			tx = ses.beginTransaction();
			
			Product pro = new Product();
			
			pro.setPname("Television");
			pro.setPrice(68000.0f);
			pro.setQty(1.0f);
			
			int idval = (Integer)ses.save(pro);
			System.out.println("Generated Id Value is "+idval);
			
			tx.commit();
			
		}
		catch(Exception e) {
			
			if(tx!= null && tx.getStatus() != null && tx.getRollbackOnly()) {
				System.out.println("Object Failed to insert");
				tx.rollback();
			}
			
			e.printStackTrace();
		}
	}

}
