package com.org.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;

public class HQLSelectTestSubQuery {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (factory; ses) {

			// Executing HQL Select Sub Query

			Query query = ses.createQuery("from Product where price=(select max(price) from Product)");

			List<Product> list = query.list();

			list.forEach(System.out::println);


		} catch (Exception e) {

		}

	}

}
