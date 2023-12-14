package com.org.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.Department;
import com.org.entity.Employee;
import com.org.entity.IDepartment;
import com.org.utility.HibernateUtil;

public class CompanyDAOImpl implements ICompanyDAO {

	@Override
	public void loadDataUsingChildWithProxy() {

		// Get Access to Session Object

		Session ses = HibernateUtil.getSession();

		try (ses) {

			Query query = ses.createQuery("from Employee");

			List<Employee> list = query.getResultList();

			list.forEach(pd -> {
				System.out.println("Partent " + pd);

				// getting Phone Number details of each Person
				IDepartment ch = pd.getDepartment(); // Lazy loading(On demand)

				System.out.println("Department = " + ch);
			});
		}

		catch (HibernateException he) {

			System.out.println(he.getMessage());
		}

	}

	

}
