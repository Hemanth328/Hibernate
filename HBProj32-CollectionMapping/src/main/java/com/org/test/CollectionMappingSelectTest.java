package com.org.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.org.entity.PersonDetails;
import com.org.utility.HibernateUtil;

public class CollectionMappingSelectTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();

		
		try (factory; ses) {
			
			// execute the query
			Query query = ses.createQuery("from PersonDetails"); // will fetch child class records as well as this is the reference of parent class
			
			List<PersonDetails> list = query.getResultList();
			
			list.forEach(pd-> {
				System.out.println("Person Info : "+pd.getPid()+"  "+pd.getPname()+"  "+pd.getPaddrs());
				
				// NickNames info
				List<String> nickNames = pd.getNickNames();
				System.out.println("NickNames : "+nickNames);
				
				// Friends
				List<String> friends = pd.getFriends();
				System.out.println("Friends : "+friends);
				
				// Contact Numbers
				Set<Long> conNumbers = pd.getContactNumbers();
				System.out.println("Get Contact Numbers : "+conNumbers);
				
				// ID Details
				Map<String, Long> idDetails = pd.getIdDetails();
				System.out.println("IdDetails : "+idDetails);
			});
			

		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}

}
