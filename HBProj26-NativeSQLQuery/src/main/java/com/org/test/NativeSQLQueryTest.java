package com.org.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;

public class NativeSQLQueryTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (factory; ses) {
			tx = ses.beginTransaction();

			/*
			NativeQuery nquery = ses.createNativeQuery("Select * from product");

			nquery.addEntity(Product.class); // Mapping results with Entity class

			
			List<Product> list = nquery.getResultList();
			
			list.forEach(System.out::println);
			*/
			
			NativeQuery nquery = ses.createNativeQuery("Select * from product");

			List<Object[]> list = nquery.getResultList();

			list.forEach(rec -> {
				for(Object val : rec) {
					System.out.print(val+"  ");
				}
				System.out.println();
			});

		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}// main

}
