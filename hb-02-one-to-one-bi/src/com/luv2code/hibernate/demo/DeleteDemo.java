package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {			
			//start transaction
			session.beginTransaction();
			
			//get instructor by primary key
			int theId=1;
			Instructor theInstructor = session.get(Instructor.class, theId);
			System.out.println("Found instructor: " + theInstructor);
			//delete instructor
			if(theInstructor!=null) {
				System.out.println("Deleting: " +theInstructor);
				//this will delete also detail object
				session.delete(theInstructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
		
	}

}
