package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Movie;
import com.org.util.HibernateUtil;

public class MovieObjectTestByAnnotations {
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			Movie mov = new Movie();
			
			mov.setMid(153);
			mov.setMname("Blue Whale");
			mov.setHero("Rajesh");
			mov.setRating(4.6f);
			mov.setBudget(500000000L);
			
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
