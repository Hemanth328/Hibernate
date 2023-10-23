package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Movie;
import com.org.util.HibernateUtil;

public class TransientAnnotationTest {
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			Movie mov = new Movie();
			
			mov.setMid(155);
			mov.setMname("The Tiger");
			mov.setHero("Ravi Teja");
			mov.setRating(4.9f);
			mov.setBudget(50000000L);
			
			ses.save(mov);
			
			System.out.println("Movie Details Saved Successfully");
			
			tx.commit();
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
