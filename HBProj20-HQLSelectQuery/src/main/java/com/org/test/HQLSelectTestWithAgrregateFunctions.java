package com.org.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;

public class HQLSelectTestWithAgrregateFunctions {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (factory; ses) {

			
			// executing HQL Select with single Aggregate function
			
			Query query = ses.createQuery("select count(*) from Product");

			Long count = (Long)query.getSingleResult();
			
			System.out.println("Total Records count in Product table is  "+count);
			
			// executing HQL Select with multiple Aggregate function
			
			Query query1 = ses.createQuery("select min(price), max(price), avg(price), sum(price) from Product");
			
			Object results[] = (Object[])query1.getSingleResult();
			System.out.println("Min Price "+results[0]);
			System.out.println("Max Price "+results[1]);
			System.out.println("Avg Price "+results[2]);
			System.out.println("Total Price "+results[3]);
			
			
		} catch (Exception e) {

		}

	}

}
