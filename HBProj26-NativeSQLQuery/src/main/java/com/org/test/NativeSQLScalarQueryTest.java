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

public class NativeSQLScalarQueryTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (factory; ses) {
			tx = ses.beginTransaction();

			NativeQuery nquery = ses
					.createNativeQuery("Select PID, PNAME, PRICE from product where PRICE BETWEEN ? AND ?");

			// Map Scalar Query results with Hibernate Data Types
			nquery.addScalar("PID", StandardBasicTypes.INTEGER);
			nquery.addScalar("PNAME", StandardBasicTypes.STRING);
			nquery.addScalar("PRICE", StandardBasicTypes.FLOAT);

			nquery.setParameter(1, 3500.0f);
			nquery.setParameter(2, 12000.0f);

			List<Object[]> list = nquery.getResultList();

			list.forEach(rec -> {
				for (Object val : rec) {
					System.out.print(val + " ");
				}

				System.out.println();
			});
			
			System.out.println();
			System.out.println();
			
			
			NativeQuery query = ses.createNativeQuery("Select * from Product where price>= :minRange and qty <= :maxQty");
			
			query.addEntity(Product.class);
			
			query.setParameter("minRange", 8000);
			query.setParameter("maxQty", 75);
			
			List<Product> list1 = query.getResultList();
			
			list1.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}

}
