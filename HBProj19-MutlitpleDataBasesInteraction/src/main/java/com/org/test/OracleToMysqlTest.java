package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil_Mysql;
import com.org.util.HibernateUtil_Oracle;

public class OracleToMysqlTest {

	public static void main(String[] args) {

		SessionFactory oraclefactory = HibernateUtil_Oracle.getSessionFactory();
		SessionFactory mysqlfactory = HibernateUtil_Mysql.getSessionFactory();
		
		Session orases = HibernateUtil_Oracle.getSession();
		Session myses = HibernateUtil_Mysql.getSession();
		
		Transaction mysTx = null;

		try (oraclefactory; mysqlfactory; orases; myses) {
			
			// get object/record from the oracle db table
			
			Product prod = orases.get(Product.class, 9);
			
			// Save the object/record into mysql db table
			if(prod == null)
				System.out.println("Record does not exist to transfer the data");
			
			mysTx = myses.beginTransaction();
			
			Integer idVal = (Integer)myses.save(prod);
			
			mysTx.commit();
			
			System.out.println("Record/Object is transferred from oracle to mysql DB table  with id "+idVal);
		}
		
		catch (Exception e) {
			if(mysTx != null && mysTx.getStatus() != null && mysTx.getRollbackOnly()) {
				mysTx.rollback();
				System.out.println("Error occured while transferring the data");
			}
			
			e.printStackTrace();
		}
	}
}
