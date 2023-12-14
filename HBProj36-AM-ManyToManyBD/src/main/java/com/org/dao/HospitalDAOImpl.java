package com.org.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Doctor;
import com.org.entity.Patient;
import com.org.utility.HibernateUtil;

public class HospitalDAOImpl implements IHospitalDAO {

	@Override
	public void saveDataUsingParent() {

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (ses) {

			// begin transaction
			tx = ses.beginTransaction();

			// Prepare objs
			Doctor doc1 = new Doctor();
			doc1.setDocName("Ramya");
			doc1.setHospital("Apollo");

			Doctor doc2 = new Doctor();
			doc2.setDocName("Ravi");
			doc2.setHospital("SSH");

			// Prepare child objs
			Patient pat1 = new Patient();
			pat1.setPatName("A K RAO");
			pat1.setProblem("Stone in Kidney");

			Patient pat2 = new Patient();
			pat2.setPatName("SomneCow");
			pat2.setProblem("Fever");

			Patient pat3 = new Patient();
			pat3.setPatName("Buffalo");
			pat3.setProblem("Dengue");

			Patient pat4 = new Patient();
			pat4.setPatName("SomneDonkey");
			pat4.setProblem("Malaria");

			// Assign child to parents
			doc1.setPatient(Set.of(pat1, pat2, pat4));
			doc2.setPatient(Set.of(pat1, pat2, pat3));

			// Assign parents to child
			pat1.setDoctor(Set.of(doc1, doc2));
			pat2.setDoctor(Set.of(doc1, doc2));
			pat3.setDoctor(Set.of(doc2));
			pat4.setDoctor(Set.of(doc1));

			// save using parents
			ses.save(doc1);
			ses.save(doc2);

			tx.commit();
			
			System.out.println();
			System.out.println("Objects saved successfully using parent");

		}

		catch (HibernateException he) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Records failed to insert using parent");
			}

			System.out.println(he.getMessage());
		}
	}

	@Override
	public void saveDataUsingChild() {
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try (ses) {

			// begin transaction
			tx = ses.beginTransaction();

			// Prepare objs
			Doctor doc1 = new Doctor();
			doc1.setDocName("Manger");
			doc1.setHospital("ILH");

			Doctor doc2 = new Doctor();
			doc2.setDocName("Swift");
			doc2.setHospital("PIH");

			// Prepare child objs
			Patient pat1 = new Patient();
			pat1.setPatName("Justin Trudao");
			pat1.setProblem("Heart and Kidney");

			Patient pat2 = new Patient();
			pat2.setPatName("Imran Khan");
			pat2.setProblem("Heart n Liver");

			Patient pat3 = new Patient();
			pat3.setPatName("Bidden");
			pat3.setProblem("Liver");

			Patient pat4 = new Patient();
			pat4.setPatName("Rahul Gandhi");
			pat4.setProblem("Brain");

			// Assign child to parents
			doc1.setPatient(Set.of(pat1, pat2, pat3, pat4));
			doc2.setPatient(Set.of(pat1, pat4));

			// Assign parents to child
			pat1.setDoctor(Set.of(doc1, doc2));
			pat2.setDoctor(Set.of(doc1));
			pat3.setDoctor(Set.of(doc1));
			pat4.setDoctor(Set.of(doc1, doc2));

			// save using parents
			ses.save(pat1);
			ses.save(pat2);
			ses.save(pat3);
			ses.save(pat4);

			tx.commit();

			System.out.println();
			System.out.println("Objects saved successfully using child");

		}

		catch (HibernateException he) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Records failed to insert using child");
			}

			System.out.println(he.getMessage());
		}

	}
	
	
	@Override
	public void loadDataUsingParent() {
		
		Session ses = HibernateUtil.getSession();
		
		try(ses) {
			Query query = ses.createQuery("from Doctor");
			
			List<Doctor> list = query.getResultList();
			
			list.forEach(doc -> {
				System.out.println("Parent : "+doc);
				
				Set<Patient> ch = doc.getPatient();
				ch.forEach(pat -> {
					System.out.println("Child : "+pat);
				});
			});
		}
	}

}
