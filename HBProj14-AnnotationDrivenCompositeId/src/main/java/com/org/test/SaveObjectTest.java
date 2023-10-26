package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.PrgmProjId;
import com.org.entity.PrgmProjectInfo;
import com.org.util.HibernateUtil;

public class SaveObjectTest {
	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (factory; ses) {

			tx = ses.beginTransaction();
			
			PrgmProjId pid = new PrgmProjId(100, 1001);

			PrgmProjectInfo ppi = new PrgmProjectInfo();

			ppi.setId(pid);
			ppi.setPrgmrName("Chekki");
			ppi.setProjName("OpenFx");
			ppi.setSalary(90000.0);
			ppi.setBudget(1500000.0);

			PrgmProjId idVal = (PrgmProjId)ses.save(ppi);

			tx.commit();

			System.out.println("Composite-Object Saved Successfully with the proj id value "+idVal.getProjId()+" and the Programmer Id value is "+idVal.getPrgmrId());
		}

		catch (Exception e) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				System.out.println("Composite-Object Failed to save");
				tx.rollback();
			}

			e.printStackTrace();
		}
	}
}
