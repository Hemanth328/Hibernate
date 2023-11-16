package com.org.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;

public class NamedHQLQueryTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (factory; ses) {
			tx = ses.beginTransaction();

			Query query1 = ses.getNamedQuery("HQL_GET_PRODUCTS_BY_PRICE_RANGE");

			query1.setParameter("min", 2000);
			query1.setParameter("max", 12000);

			List<Product> list = query1.getResultList();

			list.forEach(System.out::println);

			Query query2 = ses.getNamedQuery("INCREASE_PRODUCT_PRICE");

			query2.setParameter("newValue", 850);
			query2.setParameter("range", 12000);

			int count = query2.executeUpdate();
			tx.commit();
			System.out.println("no.of records that are effected::" + count);
			
		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}// main

}
