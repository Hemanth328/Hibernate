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

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class JPAQBCSelectConditionTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (factory; ses) {
			tx = ses.beginTransaction();
			
			//create CriteriaBulder  obj
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			
			//create CriteriaQuery object
			CriteriaQuery<Product> ctQuery = ctBuilder.createQuery(Product.class);
			
			// create Root object specifying the form operation
			Root<Product> root = ctQuery.from(Product.class); // select * from product
			
			/*
			// Fetching the records based on Price
			
			//apply where clause condition
			ctQuery.select(root).where(ctBuilder.and(ctBuilder.ge(root.get("price"), 2000),
					                                 ctBuilder.ge(root.get("price"), 13500)));
			
			// prepare Query Object having CritertiaQuery obj
			Query query = ses.createQuery(ctQuery);
			
			//execute the QBC logics
			List<Product> list = query.getResultList();
			
			list.forEach(System.out::println);
			
			*/
			
			/*
			// Fetching the records based on product names
			
			//apply where and in clause condition
			ctQuery.select(root).where(root.get("pname").in("chairs", "Pendrive", "Pens")).orderBy(ctBuilder.asc(root.get("price")));
			
			//prepare Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			
			//execute the QBC logics
			List<Product> list = query.getResultList();
			
			//process the result
			list.forEach(System.out::println);
			
			*/
			
			
			/*
			// Fetching the records based on letters
			
			// apply where clause condition
			ctQuery.select(root).where(ctBuilder.like(root.get("pname"), "a%"));
			
			//prepare Query object having CriteriaQuery obj
			Query query = ses.createQuery(ctQuery);
			
			//execute the QBC logics
			List<Product> list = query.getResultList();
			
			//process the result
			list.forEach(System.out::println);
			*/
			
			
			// Fetching the records based on qty range
			
			// Applying the required condition
			ctQuery.select(root).where(ctBuilder.and(ctBuilder.ge(root.get("qty"), 15),
					                                ctBuilder.le(root.get("qty"), 80)));
			
			// prepare Query object having CriteriaQuery obj
			Query query = ses.createQuery(ctQuery);
			
			// executing the logics
			List<Product> list = query.getResultList();
			
			//process the result
			list.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}// main

}
