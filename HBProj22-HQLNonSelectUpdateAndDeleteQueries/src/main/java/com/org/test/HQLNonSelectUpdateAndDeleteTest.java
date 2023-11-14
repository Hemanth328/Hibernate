package com.org.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;

public class HQLNonSelectUpdateAndDeleteTest {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (factory; ses) {

			// Executing HQL Non Select Update Query
			
			//Begin Tx
			tx = ses.beginTransaction();
			
			Query query1 = ses.createQuery("update Product set price = price+:amount where price>= :min and price <= :max");
			
			query1.setParameter("amount", 1250);
			query1.setParameter("min", 3000);
			query1.setParameter("max", 12000);
			
			int count1 = query1.executeUpdate();
			
			tx.commit();
			
			System.out.println("No.Of Records that are effected "+count1);
			

			// Executing HQL Non Select Delete Query

			// Begin Tx
			tx = ses.beginTransaction();

			Query query2 = ses.createQuery("delete from Product where qty>= :min and qty <= :max");

			query2.setParameter("min", 5);
			query2.setParameter("max", 10);

			int count2 = query2.executeUpdate();

			tx.commit();

			System.out.println("No.Of Records that are effected " + count2);

		} catch (Exception e) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				System.out.println(" ");
				tx.rollback();
			}
			e.printStackTrace();
		}

	}

}
