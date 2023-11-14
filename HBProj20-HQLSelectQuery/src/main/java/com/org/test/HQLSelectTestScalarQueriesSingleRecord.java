package com.org.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Product;
import com.org.utility.HibernateUtil;

public class HQLSelectTestScalarQueriesSingleRecord {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (factory; ses) {

			// Executing HQL Select Query giving Single record

			Query query1 = ses.createQuery("from Product where pid= :id");

			query1.setParameter("id", 12);

			Product prod1 = (Product) query1.getSingleResult();

			if (prod1 == null)
				System.out.println("Record Not Found");
			else
				System.out.println(prod1);

			// Executing HQL Select Query giving Single record with multiple column values

			Query query2 = ses.createQuery("select pid, pname, price from Product where pid= :id");

			query2.setParameter("id", 14);

			Object result = query2.getSingleResult();

			Object values[] = (Object[]) result;

			System.out.println(values[0] + "  " + values[1] + "  " + values[2]);

			// Executing HQL Select Query giving Single record single column value

			Query query3 = ses.createQuery("select pname from Product where pid= :id");

			query3.setParameter("id", 13);

			String name = (String)query3.getSingleResult();
			
			System.out.println("12 id Product "+name);
			
			// executing HQL Select scalar query giving single record single column value
			
			Query query4 = ses.createQuery("select count(*) from Product");

			Long count = (Long)query4.getSingleResult();
			
			System.out.println("Total Records count in Product table is  "+count);

		} catch (Exception e) {

		}

	}

}
