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

public class ComponentMappingSelectTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		
		try (factory; ses) {
			
			// begin transaction
			tx = ses.beginTransaction();
			
			Query query1  = ses.createQuery("from Person");
			
			// execute the query
			List<Person> list1 = query1.getResultList();
			
			list1.forEach(per -> {
				System.out.println("Person details :: "+per.getPid()+"  "+per.getPname()+"  "+per.getPaddrs());
				System.out.println("Job Details "+per.getDetails().getDesg()+"  "+per.getDetails().getSalary()+"  "+per.getDetails().getCompany());
			});
			
			// execute HQL/JPQL Query
			Query query2 = ses.createQuery("from Person where details.company = ?1");
			
			query2.setParameter(1, "HCL");
			
			List<Person> list2 = query2.getResultList();
			list2.forEach(per -> {
				System.out.println("Person details :: "+per.getPid()+"  "+per.getPname()+"  "+per.getPaddrs());
				System.out.println("Job Details "+per.getDetails().getDesg()+"  "+per.getDetails().getSalary()+"  "+per.getDetails().getCompany());
			});
			  

		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in HQL INSERT Query execution ");
			}
		}
	}

}
