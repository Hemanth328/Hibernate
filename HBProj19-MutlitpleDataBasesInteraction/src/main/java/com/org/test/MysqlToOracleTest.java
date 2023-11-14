package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil_Mysql;
import com.org.util.HibernateUtil_Oracle;

public class MysqlToOracleTest {

	public static void main(String[] args) {

		SessionFactory oraclefactory = HibernateUtil_Oracle.getSessionFactory();
		SessionFactory mysqlfactory = HibernateUtil_Mysql.getSessionFactory();
		
		Session orases = HibernateUtil_Oracle.getSession();
		Session myses = HibernateUtil_Mysql.getSession();
		
		Transaction oraTx = null;

		try (oraclefactory; mysqlfactory; orases; myses) {
			
			// get object/record from the mysql db table
			
			Product prod = myses.get(Product.class, 12);
			
			// Save the object/record into mysql db table
			if(prod == null)
				System.out.println("Record does not exist to transfer the data");
			
			System.out.println(prod);
			
			oraTx = orases.beginTransaction();
			
			Integer idVal = (Integer)orases.save(prod);
			
			oraTx.commit();
			
			System.out.println("Record/Object is transferred from mysql to oracle DB table with id "+idVal);
		}
		
		catch (Exception e) {
			if(oraTx != null && oraTx.getStatus() != null && oraTx.getRollbackOnly()) {
				oraTx.rollback();
				System.out.println("Error occured while transferring the data");
			}
			
			e.printStackTrace();
		}
	}
}
