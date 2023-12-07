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
import com.org.utility.HibernateUtil;

public class CompanyDAOImpl implements ICompanyDAO {

	@Override
	public void saveDataUsingParent() {
		// get access to Session Object
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			tx = ses.beginTransaction();

			// Prepare Parent Obj
			Department dept = new Department();
			dept.setDeptName("Development");
			dept.setDeptHead("Hemanth");

			// Prepare child objs
			Employee emp1 = new Employee();
			emp1.setEmpName("Hemnanth Kumar");
			emp1.setEmpSalary(195000.0);

			Employee emp2 = new Employee();
			emp2.setEmpName("Ramesh");
			emp2.setEmpSalary(155000.0);

			// set child to parent (for one to many)
			dept.setEmployee(Set.of(emp1, emp2));

			// set parent to child (for many to one)
			emp1.setDepartment(dept);
			emp2.setDepartment(dept);

			// save through parent
			int idVal = (int) ses.save(dept);

			tx.commit();

			System.out.println("Record inserted successfully with Id " + idVal);

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
	public void saveDataUsingChild() {

		// get access to Session Object
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			tx = ses.beginTransaction();

			// Prepare Parent Obj
			Department dept = new Department();
			dept.setDeptName("Testing");
			dept.setDeptHead("Shiva");

			// Prepare child objs
			Employee emp1 = new Employee();
			emp1.setEmpName("Niharika");
			emp1.setEmpSalary(120000.0);

			Employee emp2 = new Employee();
			emp2.setEmpName("Chandu");
			emp2.setEmpSalary(112000.0);

			// set child to parent (for one to many)
			dept.setEmployee(Set.of(emp1, emp2));

			// set parent to child (for many to one)
			emp1.setDepartment(dept);
			emp2.setDepartment(dept);

			// save through child
			int idVal1 = (int) ses.save(emp1);
			int idVal2 = (int) ses.save(emp2);

			tx.commit();

			System.out.println("Record inserted successfully via child table with Ids " + idVal1+" and "+idVal2);

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

			Query query = ses.createQuery("from Department");

			List<Department> list = query.getResultList();

			list.forEach(pd -> {
				System.out.println("Partent " + pd);

				// getting Employee details of each Person
				Set<Employee> ch = pd.getEmployee(); // Lazy loading(On demand)

				System.out.println("Child Objects count = " + ch.size());

				ch.forEach(System.out::println);
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

			Query query = ses.createQuery("from Employee");

			List<Employee> list = query.getResultList();

			list.forEach(pd -> {
				System.out.println("Child " + pd);

				// getting Phone Number details of each Person
//				Department ch = pd.getDepartment(); // Lazy loading(On demand)

//				System.out.println("Department = "+ch);
			});
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

			tx.commit();

			System.out.println("Child Record inserted Successfully");
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

			tx.commit();

			System.out.println("Child Record deleted Successfully");
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
	public void deleteOneChildFromCollectionOfParent() {

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			tx = ses.beginTransaction();

			tx.commit();

			System.out.println("Specific Record deleted from colection of records");
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

			tx.commit();

			System.out.println("Parent andd child object deleted successfully");
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
