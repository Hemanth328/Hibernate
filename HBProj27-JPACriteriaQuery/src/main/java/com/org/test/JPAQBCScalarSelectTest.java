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

public class JPAQBCScalarSelectTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (factory; ses) {
			tx = ses.beginTransaction();
			
			//create CriteriaBulder  obj
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
		
			/*
			// Fetching multiple column values
			
			//create CriteriaQuery object
			CriteriaQuery<Object[]> ctQuery = ctBuilder.createQuery(Object[].class);
			
			// create Root object specifying the form operation
			Root<Product> root = ctQuery.from(Product.class); // select * from product
			
			//specific multiple cols and where clause conditions
			ctQuery.multiselect(root.get("pid"), root.get("pname")).where(ctBuilder.and(ctBuilder.ge(root.get("price"), 2000),
					                                                      ctBuilder.le(root.get("price"), 13500))).orderBy(ctBuilder.asc(root.get("pname")));
			// select * from product where price >= ? and price <= ? order by pname asc;
			
			// create Query object having criteriaQuery object
			Query query = ses.createQuery(ctQuery);
			
			//execute the QBC logic
			List<Object[]> list = query.getResultList();
			
			//process the result
			list.forEach(row -> {
				for(Object val : row) {
					System.out.print(val+" ");
				}
				
				System.out.println();
			});
			*/
			
			/*
			// Fetching single column value (here String values are being fetched)
			
			//create CriteriaQuery object
			CriteriaQuery<String> ctQuery = ctBuilder.createQuery(String.class);
			
			// create Root object specifying the form operation
			Root<Product> root = ctQuery.from(Product.class); // select * from product
			
			//specific multiple cols and where clause conditions
			ctQuery.multiselect(root.get("pname")).where(ctBuilder.like(root.get("pname"), "a%"));
			// select * from product where pname like 'a%';
			
			// create Query object having criteriaQuery object
			Query query = ses.createQuery(ctQuery);
			
			//execute the QBC logic
			List<String> list = query.getResultList();
			
			//process the result
			list.forEach(System.out::println);
			*/
			
			/*
			// Single Aggregate Function
			
			//create CriteriaQuery object
			CriteriaQuery<Long> ctQuery = ctBuilder.createQuery(Long.class);
			
			// create Root object specifying the form operation
			Root<Product> root = ctQuery.from(Product.class); // select * from product
			
			//specific multiple cols and where clause conditions
			ctQuery.multiselect(ctBuilder.count(root.get("pid")));
			// select count(pid) from product;
			
			// create Query object having criteriaQuery object
			Query query = ses.createQuery(ctQuery);
			
			//execute the QBC logic
			Long count = (Long)query.getSingleResult();
			
			//process the result
			System.out.println("Total records = "+count);
			*/
			
			

			// Multiple Aggregate Functions
			
			//create CriteriaQuery object
			CriteriaQuery<Object[]> ctQuery = ctBuilder.createQuery(Object[].class);
			
			// create Root object specifying the form operation
			Root<Product> root = ctQuery.from(Product.class); // select * from product
			
			//specific multiple cols and where clause conditions
			ctQuery.multiselect(ctBuilder.count(root.get("pid")), ctBuilder.sum(root.get("price")), ctBuilder.avg(root.get("price")), ctBuilder.min(root.get("price")), ctBuilder.max(root.get("price")));
			// select count(pid) from product;
			
			// create Query object having criteriaQuery object
			Query query = ses.createQuery(ctQuery);
			
			//execute the QBC logic
			Object[] obj = (Object[])query.getSingleResult();
			
			System.out.println("Total count = "+obj[0]);
			System.out.println("Total sum price = "+obj[1]);
			System.out.println("Avg price = "+obj[2]);
			System.out.println("Min price = "+obj[3]);
			System.out.println("Max price = "+obj[4]);
			
			
			
			
		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}// main

}
