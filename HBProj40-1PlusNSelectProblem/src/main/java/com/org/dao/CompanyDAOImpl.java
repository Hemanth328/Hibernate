package com.org.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.org.entity.Department;
import com.org.entity.Employee;
import com.org.utility.HibernateUtil;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public class CompanyDAOImpl implements ICompanyDAO {

	@Override
	public void loadDataByParentUsingHQLJoin() {

		// Get Access to Session Object

		Session ses = HibernateUtil.getSession();

		try (ses) {

			Query query = ses.createQuery("select d.deptNo, d.deptName, d.deptHead, e.empNo, e.empName, e.empSalary from Department d inner join d.employee e ");
//			Query query = ses.createQuery("select d.deptNo, d.deptName, d.deptHead, e.empNo, e.empName, e.empSalary from Department d left join d.employee e ");
//			Query query = ses.createQuery("select d.deptNo, d.deptName, d.deptHead, e.empNo, e.empName, e.empSalary from Department d right join d.employee e ");
//			Query query = ses.createQuery("select d.deptNo, d.deptName, d.deptHead, e.empNo, e.empName, e.empSalary from Department d full join d.employee e ");

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
	public void loadDataByChildUsingHQLJoin() {

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
	
	@Override
	public void loadDataUsingJPAQBC() {
		
		Session ses = HibernateUtil.getSession();
		
		try(ses) {
			
			// create CriteriaBuilder obj
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			
			// create CriteriaQuery object
			CriteriaQuery<Department> ctQuery = ctBuilder.createQuery(Department.class);
			
			// create Root object specifying the from operation
			Root<Department> root = ctQuery.from(Department.class); // select from operation
			root.fetch("employee", JoinType.INNER);
			
			// create Query object having Criteria Query object
			Query query = ses.createQuery(ctQuery);
			
			// execute the QBC logic
			List<Department> list = query.getResultList();
			
			// process the result
			list.forEach(dept -> {
				System.out.println("Parent = "+dept);
				
				Set<Employee> child = dept.getEmployee();
				
				child.forEach(emp -> {
					System.out.println("Child = "+emp);
				});
			});
		}
		
		catch (HibernateException he) {

			System.out.println(he.getMessage());
		}
	}

}
