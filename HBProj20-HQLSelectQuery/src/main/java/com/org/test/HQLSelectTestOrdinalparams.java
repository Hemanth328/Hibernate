package com.org.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;


public class HQLSelectTestOrdinalparams {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			
			Query query = ses.createQuery("from Product where pid>=?1 and pid<=?2");
			
			query.setParameter(1, 14);
			query.setParameter(2, 18);
			
			List<Product> list = query.list(); // by default list performs eager instantiation
			
			//			list.forEach(System.out::println);
			
			for(Product p : list) {
				System.out.println(p);
			}
			
			
		}
		catch(Exception e) {
			
		}

	}

}
