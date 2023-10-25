package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.CallerTune;
import com.org.util.HibernateUtil;

public class UpdatingExistingObject {
	
	public static void main(String[] args) {
	
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			CallerTune tune = ses.get(CallerTune.class, 2);
			
			if(tune == null) {
				System.out.println("Record does not exist");
				return;
			}
			else {
				tx = ses.beginTransaction();
				
				tune.setMovieName("Major");
				tune.setTuneName("Jaya Ho");
				
				ses.update(tune);
				
				tx.commit();
				System.out.println("Record Updated Successfully");
				
				System.out.println("Record Updated for "+tune.getUpdationCount()+" times");
				
				System.out.println("Record Was Inserted on  "+ tune.getLaunchTime()+"  Last Record Updation time  "+tune.getUpdationTime());
			}
		}
		
		catch(Exception e) {
			
			if(tx!= null && tx.getStatus()!= null && tx.getRollbackOnly()) {
				System.out.println("Record failed to Update");
				tx.rollback();
			}
		}
	}
}
