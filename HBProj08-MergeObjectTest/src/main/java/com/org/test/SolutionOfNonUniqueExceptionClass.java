package com.org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.org.entity.Product;
import com.org.util.HibernateUtil;

public class SolutionOfNonUniqueExceptionClass {
	
public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = null;
		
		try(factory; ses) {
			
			tx = ses.beginTransaction();
			
			Product prod = ses.get(Product.class, 10);
			
			Product pro = new Product();
			pro.setPid(1805);
			pro.setPname("Wifi Pre Router2");
			pro.setPrice(5600.00f);
			pro.setQty(1.00f);
			
			System.out.println(prod);
			System.out.println(prod.hashCode()+"  "+pro.hashCode());
			
			Product prod1 = ses.merge(pro);
			System.out.println(prod.hashCode()+" "+pro.hashCode()+" "+prod1.hashCode());
			System.out.println(prod1);
			System.out.println(prod);
			
			tx.commit();
			
			System.out.println("Object saved");
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}

}
