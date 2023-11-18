package com.org.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;

public class NamedNativeSQLQueryTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (factory; ses) {
			tx = ses.beginTransaction();

			NativeQuery nquery = ses.getNamedNativeQuery("GET_PRODS_BY_PRICE_RANGE");
			
			//Map Entity query results with Entity query
			nquery.addEntity(Product.class);
			
			nquery.setParameter(1, 2000);
			nquery.setParameter(2, 13000);
			
			List<Product> list = nquery.getResultList();
			
			list.forEach(System.out::println);
			
			System.out.println();
			System.out.println();
			
			
			NativeQuery query = ses.getNamedNativeQuery("HIKE_PRICE_BY_PROD_NAME");
			
			query.addEntity(Product.class);
			
			query.setParameter("incAmount", 620);
			query.setParameter("name", "Chairs");
			
			int count = query.executeUpdate();
			
			tx.commit();
			
			System.out.println("Number of recors updated = "+count);

		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}

}
