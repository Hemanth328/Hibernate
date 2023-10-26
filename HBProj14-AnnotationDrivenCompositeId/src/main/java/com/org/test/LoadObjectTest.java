package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.PrgmProjId;
import com.org.entity.PrgmProjectInfo;
import com.org.util.HibernateUtil;

public class LoadObjectTest {
	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		Session ses = HibernateUtil.getSession();

//		Transaction tx = null;

		try (factory; ses) {
			
			PrgmProjId pid = new PrgmProjId(100, 1001);

			PrgmProjectInfo ppi = ses.get(PrgmProjectInfo.class, pid);

			if (ppi == null) {
				System.out.println("Composite Object Not Found");
				return;
			}
			else {
//				tx = ses.beginTransaction();
				
				System.out.println(ppi);
			}

		} catch (Exception e) {

			/*if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				System.out.println("Composite-Object Failed to Update");
				tx.rollback();
			}*/

			e.printStackTrace();
		}
	}
}
