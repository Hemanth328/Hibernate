package com.org.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.org.entity.CardPayment;
import com.org.entity.ChequePayment;
import com.org.entity.Payment;
import com.org.utility.HibernateUtil;

public class TablePerSubClassSelectTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		
		try (factory; ses) {
			
			// execute the query
			Query query1 = ses.createQuery("from Payment"); // will fetch child class records as well as this is the reference of parent class
			
			List<Payment> list1 = query1.getResultList();
			
			list1.forEach(System.out::println);
			System.out.println();
			
			System.out.println("=======================================");
			System.out.println();
			
			Query query2 = ses.createQuery("from ChequePayment");
			
			List<ChequePayment> list2 = query2.getResultList();
			
			list2.forEach(System.out::println);
			System.out.println();
			
			System.out.println("=======================================");
			System.out.println();
			
			Query query3 = ses.createQuery("from CardPayment");
			
			List<CardPayment> list3 = query3.getResultList();
			
			list3.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}

}
