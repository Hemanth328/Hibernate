package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Movie;
import com.org.util.HibernateUtil;

public class LoadObjectTestUsingLoadMethod {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			Movie mov = ses.load(Movie.class, 155);
			
			if(mov!=null) {
				System.out.println(mov);
				System.out.println("Checking for Eager loading");
			} else
				System.out.println("Details does not exist");
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}

	}
	
	// When Entity class implements IMovie Interface and proxy(lazy = false) is enabled Eager instantiation takes place irrespective of movie property is being invoked/printed
	
	// When the entity class is declared as final eager loading takes place
	
	// By default lazy loading takes place
	
	// When @Proxy(lazy = false) is annotated in entity class eager loading takes place
	// otherwise lazy loading takes place

}
