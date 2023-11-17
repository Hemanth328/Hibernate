package com.org.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;

public class PaginationTest1 {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (factory; ses) {
			
			int pSize = 5;
			tx = ses.beginTransaction();
			
			Query query1 = ses.createQuery("select count(*) from Product");
			
			long trCount = (Long)query1.getSingleResult();
			long tRecords = trCount/pSize;
			
			if(trCount%pSize != 0)
				tRecords++;
				

			Query query = ses.createQuery("from Product");
			
			for(int i = 1; i<=tRecords; i++) {
				int startPageSize = (i*pSize)-pSize;
				
				query.setFirstResult(startPageSize);
				query.setMaxResults(pSize);
				
				List<Product> list = query.getResultList();
				
				list.forEach(System.out::println);
				
				System.out.println("======================================================");
				
			}
			
			/*
			 * i = page Number
			(i*pSize)-pSize; this will give the result of record number that is from which record onwards
			 it should include in the current page
			*/
			
		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}// main

}
