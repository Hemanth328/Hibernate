package com.org.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.PersonDetails;
import com.org.utility.HibernateUtil;


public class CollectionMappingInsertTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		
		try (factory; ses) {
			
			// begin transaction
			tx = ses.beginTransaction();
			
			// Prepare Objects
			PersonDetails details = new PersonDetails();
			details.setPname("Hemanth");
			details.setNickNames(List.of("Hemanth", "Jagadesh"));
			details.setPaddrs("Hyderabad");
			details.setFriends(List.of("Deeksha", "Reyansh", "Chekki", "Kartheek"));
			details.setContactNumbers(Set.of(7702273685L, 6303852614L));
			details.setIdDetails(Map.of("Aadhar", 686372811274L, "VoterId", 457863L));
			
			// Save objects
			int idval = (int)ses.save(details);
			
			tx.commit();
			
			System.out.println("Object's have been saved successfully with id val "+idval);;

		} catch (HibernateException he) {
			
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object's have failed to save");
			}
			
			he.printStackTrace();
		}
	}

}
