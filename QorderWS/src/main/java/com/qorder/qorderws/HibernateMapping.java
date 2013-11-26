package com.qorder.qorderws;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public class HibernateMapping {
	
public static void main(String[] args) {
		
		//Student_Info student = new Student_Info();
		
		//student.setName("Pakis");
		//student.setBirthDate(new Date());
		
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//session.save(student);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		

	}
	

}
