package com.org.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;

public class HQLSelectTestScalarQueriesMultipleRecords {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (factory; ses) {

			// Executing HQL Select Query(retrieving specific multiple column values)

			Query query1 = ses.createQuery("select pid, pname, price from Product where price>= :min and price<= :max");

			query1.setParameter("min", 12000);
			query1.setParameter("max", 500000);

			List<Object[]> list1 = query1.list();

			list1.forEach(row -> {
				for (Object val : row) {
					System.out.print(val + " ");
				}
				System.out.println();
			});

			// Executing HQL Select Query(retrieving specific single column value)

			Query query2 = ses.createQuery("select pname from Product where qty>= :min and qty<= :max");

			query2.setParameter("min", 10);
			query2.setParameter("max", 50);

			List<Object> list2 = query2.list();

			list2.forEach(System.out::println);

		} catch (Exception e) {

		}

	}

}
