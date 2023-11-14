package com.org.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;


public class HQLSelectTestNamedparams {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			
			Query query = ses.createQuery("from Product where pname in(?1, :prod2, :prod3)");
			
			query.setParameter(1, "chairs");
			query.setParameter("prod2", "Apple Macbook");
			query.setParameter("prod3", "Dinner Set");
			
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
