package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.CallerTune;
import com.org.util.HibernateUtil;

public class SaveCallerTuneObjectTest {
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			CallerTune tune = new CallerTune();
			
			tune.setMovieName("Pushpa");
			tune.setTuneName("SriValli");
			
			ses.save(tune);
			
			tx.commit();
			System.out.println("Object saved successfully");
		}
		catch(Exception e) {
			
			if(tx != null || tx.getStatus()!= null || tx.getRollbackOnly()) {
				System.out.println("Object Failed to save");
				tx.rollback();
			}
		}
	}

}
