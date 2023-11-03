package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Movie;
import com.org.util.HibernateUtil;

public class SaveObjectWithJPAGenerators {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (factory; ses) {

			for (int i = 0; i < 4; i++) {

				tx = ses.beginTransaction();

				Movie mov = new Movie();

				mov.setMname("Black Dog");
				mov.setHero("Henriques");
				mov.setRating(3.8f);
				mov.setBudget(89000000000L);
				
				Thread.sleep(10000);

				Integer idVal = (Integer) ses.save(mov);

				tx.commit();

				System.out.println("Dynamic Object Inserted Successfully with id " + idVal);
			}
		} catch (Exception e) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object Failed to Insert");
			}

			e.printStackTrace();
		}

	}

}
