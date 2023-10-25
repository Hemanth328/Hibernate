package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.CallerTune;
import com.org.util.HibernateUtil;

public class UpdateCallerTuneObjectTest {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			CallerTune tune = ses.get(CallerTune.class, 1);
			
			if(tune == null) {
				System.out.println("No Record Exists to Update");
				return;
			}else {
				tx = ses.beginTransaction();
				
				tune.setTuneName("Saami Saami");
				tune.setMovieName("Pushpa");
				
				ses.update(tune);
				tx.commit();
				System.out.println("Object Updated Successfully and the Version count is "+tune.getUpdationCount());
			}
		}
		catch(Exception e) {
			
			if(tx!= null || tx.getStatus() != null || tx.getRollbackOnly()) {
				System.out.println("Object Failed to Update");
				tx.rollback();
			}
			
			e.printStackTrace();
		}

	}

}
