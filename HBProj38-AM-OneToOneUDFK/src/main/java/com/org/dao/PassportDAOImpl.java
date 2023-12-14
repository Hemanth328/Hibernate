package com.org.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Person;
import com.org.entity.Passport;
import com.org.utility.HibernateUtil;

public class PassportDAOImpl implements IPassPortDAO {

	@Override
	public void saveDataUsingChild() {

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			// begin Transaction
			tx = ses.beginTransaction();

			Person per = new Person(); // Parent
			per.setPName("Hemanth Kumar");
			per.setPAddrs("BBL");
			
			Passport pp = new Passport(); // Child
			pp.setCountry("India");
			pp.setExpiryDate(LocalDate.of(2033, 12, 5));
			
			// set parent to child
			pp.setPersonDetails(per);
			
			ses.save(pp);
			
			tx.commit();
			
			System.out.println("Record saved successfully using Child");

		}
		
		catch (HibernateException he) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Records failed to insert");
			}

			System.out.println(he.getMessage());
		}
	}
	
	
	@Override
	public void loadDataUsingChild() {
		
		Session ses = HibernateUtil.getSession();
		
		try(ses) {
			
			Query query = ses.createQuery("from Passport");
			
			List<Passport> list = query.getResultList();
			
			list.forEach(pp -> {
				
				System.out.println("Passport : "+pp);
				
				Person per = pp.getPersonDetails();
				System.out.println("Person : "+per);
			});
		}
		
		catch (HibernateException he) {

			System.out.println(he.getMessage());
		}
	}
	
	@Override
	public void loadDataUsingParent() {
		
		Session ses = HibernateUtil.getSession();
		
		try(ses) {
			
			Query query = ses.createQuery("from Person");
			
			List<Person> list = query.getResultList();
			
			list.forEach(per -> {
				
				System.out.println("Person : "+per);
				
				Passport pp = per.getPassport();
				System.out.println("Passport : "+pp);
			});
		}
		
		catch (HibernateException he) {

			System.out.println(he.getMessage());
		}
	}
}
