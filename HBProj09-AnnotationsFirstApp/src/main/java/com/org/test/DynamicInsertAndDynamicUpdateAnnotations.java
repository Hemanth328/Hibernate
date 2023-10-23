package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Movie;
import com.org.util.HibernateUtil;


public class DynamicInsertAndDynamicUpdateAnnotations {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			Movie mov = new Movie();
			
			mov.setMid(159);
			mov.setMname("Black Dog");
			mov.setHero("Henriques");
//			mov.setRating(3.8f);
//			mov.setBudget(89000000000L);
			
			ses.saveOrUpdate(mov);
			
			tx.commit();
			
			System.out.println("Dynamic Object Inserted Successfully");
		}
		catch(Exception e) {
			
			if(tx != null && tx.getStatus()!= null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object Failed to Insert");
			}
			
			e.printStackTrace();
		}

	}

}
