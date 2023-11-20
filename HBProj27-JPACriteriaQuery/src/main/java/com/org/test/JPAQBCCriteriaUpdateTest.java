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
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

public class JPAQBCCriteriaUpdateTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (factory; ses) {
			tx = ses.beginTransaction();
			
			//create CriteriaBulder  obj
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
		
			//create CriteriaUpdate object
			CriteriaUpdate<Product> ctUpdate = ctBuilder.createCriteriaUpdate(Product.class);
			
			// create Root object specifying the from object
			Root<Product> root = ctUpdate.from(Product.class);
			
			//specify values to set and the conditions to apply
			ctUpdate.set("price", 5950.0f).where(ctBuilder.and(ctBuilder.ge(root.get("qty"), 5),
					                                        ctBuilder.le(root.get("qty"), 9)));
			
			// Creating Query object with criteria update object
			Query query = ses.createQuery(ctUpdate);
			
			// executing the query object
			int count = query.executeUpdate();
			
			tx.commit();		
			
			System.out.println("Number of records updated = "+count);
			
		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}// main

}
