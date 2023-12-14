package com.org.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.org.entity.Department;
import com.org.entity.Employee;
import com.org.utility.HibernateUtil;

import jakarta.persistence.Query;

public class CompanyDAOImpl implements ICompanyDAO {

	@Override
	public void loadDataUsingParent() {

		// Get Access to Session Object

		Session ses = HibernateUtil.getSession();

		try (ses) {

//			Query query = ses.createQuery("select d.deptNo, d.deptName, d.deptHead, e.empNo, e.empName, e.empSalary from Department d inner join d.employee e ");
//			Query query = ses.createQuery("select d.deptNo, d.deptName, d.deptHead, e.empNo, e.empName, e.empSalary from Department d left join d.employee e ");
//			Query query = ses.createQuery("select d.deptNo, d.deptName, d.deptHead, e.empNo, e.empName, e.empSalary from Department d right join d.employee e ");
			Query query = ses.createQuery("select d.deptNo, d.deptName, d.deptHead, e.empNo, e.empName, e.empSalary from Department d full join d.employee e ");

			List<Object[]> list = query.getResultList();

			list.forEach( rec -> {
				
				for(Object val : rec) {
					System.out.print(val+" ");
				}
				
				System.out.println();
			});
			
		}

		catch (HibernateException he) {

			System.out.println(he.getMessage());
		}
	}
	
	
	@Override
	public void loadDataUsingChild() {

		// Get Access to Session Object

		Session ses = HibernateUtil.getSession();

		try (ses) {

			Query query = ses.createQuery("select e.empNo, e.empName, e.empSalary, d.deptNo, d.deptName, d.deptHead from Employee e right join e.department d");

			List<Object[]> list = query.getResultList();

			list.forEach( rec -> {
				
				for(Object val : rec) {
					System.out.print(val+" ");
				}
				
				System.out.println();
			});
			
		}

		catch (HibernateException he) {

			System.out.println(he.getMessage());
		}
	}

}
