package com.org.test;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.CardPayment;
import com.org.entity.ChequePayment;
import com.org.utility.HibernateUtil;


public class TablePerConcreteClassInsertTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		
		try (factory; ses) {
			
			// begin transaction
			tx = ses.beginTransaction();
			
			// Prepare Objects
			ChequePayment cqPay = new ChequePayment();
			cqPay.setAmount(1000000.0);
			cqPay.setChequeNo(877684L);
			cqPay.setChequeType("self");
			cqPay.setTxDate(LocalDateTime.of(2022,12,11,10,30,12));
						
			CardPayment card = new CardPayment();
			card.setAmount(26533.0);
			card.setCardNo(1234567890011122L);
			card.setTxDate(LocalDateTime.of(2022,11,21,14,52,36));
			card.setCardType("CREDIT");
			card.setGateway("visa Signature");
			
			// Save objects
			ses.save(cqPay);
			ses.save(card);
			
			tx.commit();
			
			System.out.println("Object's have been saved successfully");

		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object's have failed to save");
			}
		}
	}

}
