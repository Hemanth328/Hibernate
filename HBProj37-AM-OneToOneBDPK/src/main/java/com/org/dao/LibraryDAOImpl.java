package com.org.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.org.entity.LibraryMemberShip;
import com.org.entity.Student;
import com.org.utility.HibernateUtil;

public class LibraryDAOImpl implements ILibraryDAO {

	@Override
	public void saveDataUsingParent() {

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			// begin Transaction
			tx = ses.beginTransaction();

			Student st = new Student();
			st.setSname("Hemanth Kumar");
			st.setSadd("vbl");

			LibraryMemberShip lib = new LibraryMemberShip();
			lib.setDoj(LocalDate.of(2022, 12, 11));
			lib.setType("Silver");
			// set parent to child
			lib.setStudentDetails(st);

			// set child to parent
			st.setLibraryDetails(lib);

			ses.save(st);

			tx.commit();

			System.out.println("Record inserted successfully using Parent");

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
	public void saveDataUsingChild() {

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			// begin Transaction
			tx = ses.beginTransaction();

			Student st = new Student();
			st.setSname("Krishna");
			st.setSadd("RJM");

			LibraryMemberShip lib = new LibraryMemberShip();
			lib.setDoj(LocalDate.of(2019, 8, 28));
			lib.setType("Gold");
			// set parent to child
			lib.setStudentDetails(st);

			// set child to parent
			st.setLibraryDetails(lib);

			ses.save(lib);

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
	public void loadDataUsingParent() {

		Session ses = HibernateUtil.getSession();

		try (ses) {

			Query query = ses.createQuery("from Student");

			List<Student> list = query.getResultList();

			list.forEach(stud -> {

				System.out.println("Student : " + stud);

//				LibraryMemberShip lib = stud.getLibraryDetails();
//				System.out.println("LibraryMemberShip : "+lib);

			});
		}

		catch (HibernateException he) {

			System.out.println(he.getMessage());
		}
	}

	@Override
	public void loadDataUsingChild() {
		
		Session ses = HibernateUtil.getSession();

		try (ses) {

			Query query = ses.createQuery("from LibraryMemberShip");

			List<LibraryMemberShip> list = query.getResultList();

			list.forEach(lib -> {

				System.out.println("LibraryMemberShip : " + lib);

				Student stud = lib.getStudentDetails();
				System.out.println("Student : " + stud);

			});
		}

		catch (HibernateException he) {

			System.out.println(he.getMessage());
		}
	}
}
