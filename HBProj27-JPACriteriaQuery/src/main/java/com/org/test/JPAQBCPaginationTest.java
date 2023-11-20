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

public class JPAQBCPaginationTest {

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
			
			// create Query object having criteriaQuery object
			Query query = ses.createQuery(ctQuery);
			
			//Perform pagination activities
			query.setFirstResult(0);
			query.setMaxResults(5);
			
			//execute the QBC logic
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
	}

}
