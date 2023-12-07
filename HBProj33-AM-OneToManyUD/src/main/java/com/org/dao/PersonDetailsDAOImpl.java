package com.org.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.PersonDetails;
import com.org.entity.PhoneNumber;
import com.org.utility.HibernateUtil;

public class PersonDetailsDAOImpl implements IPersonDetailsDAO {

	@Override
	public void saveDataUsingParent() {
		// get access to Session Object
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			tx = ses.beginTransaction();

			// Prepare Parent Obj
			PersonDetails pd = new PersonDetails();
			pd.setPname("Hemanth");
			pd.setPaddrs("Bobbili");

			// Prepare Child class objs
			PhoneNumber ph1 = new PhoneNumber();
			ph1.setMobileNo(99999999L);
			ph1.setNumberType("Personal");
			ph1.setProvider("VI");

			PhoneNumber ph2 = new PhoneNumber();
			ph2.setMobileNo(888888888L);
			ph2.setNumberType("Corporate");
			ph2.setProvider("DOCOMO");

			// set Child Details to Parent Obj
			pd.setPhoneNumbers(Set.of(ph1, ph2));

			// Save Parent Obj
			int idVal = (int) ses.save(pd);

			tx.commit();

			System.out.println("Record inserted successfully with Id " + idVal);
			System.out.println();
			System.out.println();

		}

		catch (HibernateException he) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				System.out.println("Record Failed to insert");
				tx.rollback();
			}

			System.out.println(he.getMessage());
		}
	}

	@Override
	public void loadDataUsingParent() {

		// Get Access to Session Object

		Session ses = HibernateUtil.getSession();

		try (ses) {

			Query query = ses.createQuery("from PersonDetails");

			List<PersonDetails> list = query.getResultList();

			list.forEach(pd -> {
				System.out.println("Partent " + pd);

				// getting Phone Number details of each Person
				Set<PhoneNumber> ch = pd.getPhoneNumbers(); // Lazy loading(On demand)

				System.out.println("Child Objects count = " + ch.size());

				ch.forEach(System.out::println);
			});
			
			System.out.println();
			System.out.println();
		}

		catch (HibernateException he) {

			System.out.println(he.getMessage());
		}
	}

	@Override
	public void addChildToExistingParent() {

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			tx = ses.beginTransaction();

			PersonDetails pd = ses.get(PersonDetails.class, 1);

			Set<PhoneNumber> ch = pd.getPhoneNumbers();

			PhoneNumber phn = new PhoneNumber();
			phn.setMobileNo(777777777L);
			phn.setNumberType("Home");
			phn.setProvider("JIO");

			ch.add(phn);

			tx.commit();

			System.out.println("Child Record inserted Successfully");
			
			System.out.println();
			System.out.println();
		}

		catch (HibernateException he) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				System.out.println("Record Failed to insert");
				tx.rollback();
			}

			System.out.println(he.getMessage());
		}
	}

	@Override
	public void deleteAllChildsOfParent() {

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			tx = ses.beginTransaction();

			PersonDetails pd = ses.get(PersonDetails.class, 1);

			Set<PhoneNumber> ch = pd.getPhoneNumbers();

			ch.removeAll(ch);

			tx.commit();

			System.out.println("Child Records deleted Successfully");
			
			System.out.println();
			System.out.println();
		}

		catch (HibernateException he) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				System.out.println("Failed to delete all child records");
				tx.rollback();
			}

			System.out.println(he.getMessage());
		}

	}

	@Override
	public void deleteOneChildFromCollectionOfParent() throws HibernateException {

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			tx = ses.beginTransaction();

			PersonDetails pd = ses.get(PersonDetails.class, 1);

			Set<PhoneNumber> ch = pd.getPhoneNumbers();

			PhoneNumber pn = ses.get(PhoneNumber.class, 1006);

			if(pn == null)
				throw new HibernateException("Object is Empty");
			ch.remove(pn);

			tx.commit();

			System.out.println("Specific Record deleted from colection of records");
			
			System.out.println();
			System.out.println();
		}

		catch (HibernateException he) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				System.out.println("Failed to delete a specific record");
				tx.rollback();
			}

			System.out.println(he.getMessage());
		}

	}

	@Override
	public void deleteParentAndChild() {

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			tx = ses.beginTransaction();

			PersonDetails pd = ses.get(PersonDetails.class, 1);

			if (pd == null)
				throw new HibernateException("Object is Empty");
			
			ses.delete(pd);

			tx.commit();

			System.out.println("Parent andd child object deleted successfully");
			
			System.out.println();
			System.out.println();
		}

		catch (HibernateException he) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				System.out.println("Failed to delete a record");
				tx.rollback();
			}

			System.out.println(he.getMessage());
		}

	}

}
