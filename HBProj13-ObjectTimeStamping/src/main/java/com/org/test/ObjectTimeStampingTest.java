package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.CallerTune;
import com.org.util.HibernateUtil;

public class ObjectTimeStampingTest {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			CallerTune tune = new CallerTune();
			
			tune.setMovieName("Alavaikuntapuram");
			tune.setTuneName("Saamajavaragamana");
			
			ses.save(tune);
			
			tx.commit();
			
			System.out.println("Record Inserted Successfully");
		}
		
		catch(Exception e) {
			
			if(tx != null && tx.getStatus()!= null && tx.getRollbackOnly()) {
				System.out.println("Object Failed to Insert");
				tx.rollback();
			}
			
			e.printStackTrace();
		}

	}

}
