package com.org.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.org.entity.JobDetails;
import com.org.entity.Person;
import com.org.utility.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ComponentMappingInsertTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		
		try (factory; ses) {
			
			// begin transaction
			tx = ses.beginTransaction();
			
			// prepare objs
			JobDetails details = new JobDetails();
			details.setDesg("ASE");
			details.setCompany("HCL");
			details.setSalary(90000.0);
			
			Person per = new Person();
			per.setPname("Vineela");
			per.setPaddrs("Vijayawada");
			per.setDetails(details);
			
			// Save objects
			Integer id = (Integer)ses.save(per);
			System.out.println("Person Object is saved with the id value = "+id);

		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}

}
