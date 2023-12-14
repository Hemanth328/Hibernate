package com.org.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.org.entity.Employee;
import com.org.utility.HibernateUtil;

public class CompanyDAOImpl implements ICompanyDAO {

	@Override
	public void saveData() {
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;

		try (ses) {

			// Read image file content to byte[]
			File file1 = new File("C:\\Users\\heman\\Pictures\\887950.jpg");
			InputStream is = new FileInputStream(file1);

			byte image[] = new byte[(int) file1.length()];
			is.read(image);

			// Read text file content to char[]
//			File file2 = new File("C:\\Users\\heman\\Downloads\\Reddy Hemanth Kumar.pdf");
			File file2 = new File("C:\\Users\\heman\\Downloads\\text.txt");
			Reader reader = new FileReader(file2);

			char text[] = new char[(int) file2.length()];
			reader.read(text);

			// Prepare Entity class object
			Employee emp = new Employee();
			emp.setEmpName("Hemanth Kumar");
			emp.setEmpSalary(180000.0);
			emp.setEmpPhoto(image);
			emp.setEmpResume(text);

			tx = ses.beginTransaction();

			int idVal = (int) ses.save(emp);

			tx.commit();

			System.out.println("Object is saved with the id value " + idVal);
		} catch (HibernateException he) {

			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Record failed to insert");
			}
			System.out.println(he.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void fetchData() {

		Session ses = HibernateUtil.getSession();

		try (ses) {
			Employee emp = ses.get(Employee.class, 952);
			
			if(emp == null)
				return;
			
			System.out.println(emp.getEmpNo()+"  "+emp.getEmpName()+"  "+emp.getEmpSalary());
			
			byte[] photo = emp.getEmpPhoto();
			char[] resume = emp.getEmpResume();
			
			// Create Output stream
			OutputStream os = new FileOutputStream("new_Pic.jpg");
			os.write(photo);;
			os.flush();
			os.close();
			
			// Create Writer stream pointing to destination file
//			Writer writer = new FileWriter("new_Resume.pdf");
			Writer writer = new FileWriter("new_text.txt");
			writer.write(resume);
			writer.flush();
			writer.close();
			
			System.out.println("LOB's Retrieved Successfully");
		}
		
		catch(HibernateException he) {
			System.out.println(he.getMessage());
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
