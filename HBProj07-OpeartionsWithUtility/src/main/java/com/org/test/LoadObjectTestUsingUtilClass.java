package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class LoadObjectTestUsingUtilClass {
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		try(factory; ses) {
			
			Product prod = ses.get(Product.class, 8);
			
			if(prod == null)
				System.out.println("Record does not exist");
			else
				System.out.println(prod + "\nRecord Fetched Successfully");
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}

}
