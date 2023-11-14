package com.org.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;


public class HQLSelectTest {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			
			Query query = ses.createQuery("from Product");
			
			List<Product> list = query.list(); // by default list performs eager instantiation
			
			//			list.forEach(System.out::println);
			
			for(Product p : list) {
				System.out.println(p);
			}
			
			
			
			/*
			Query query = ses.createQuery("from Product");
			
			Iterator<Product> pi = query.iterate(); // This method does not exist any more in hibernate-core 6.x
			
			while(pi.hasNext()) {
				Product p = pi.next();
				System.out.println(p.getPid()+"  "+p.getPname()+"  "+p.getPrice()+"  "+p.getQty());
			}
			*/
			
		}
		catch(Exception e) {
			
		}

	}

}
